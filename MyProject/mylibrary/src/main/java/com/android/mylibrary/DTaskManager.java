package com.android.mylibrary;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.util.SparseArray;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 作者：flyframe on 2017/3/1 15:16
 * 邮箱：jxsm6@163.com
 */
public class DTaskManager {
    public boolean isDestroy = false;
    private Context context;

    /** 线程队列 */
    private LinkedList<Thread> threadQueue;
    /** 容器 */
    private SparseArray<Thread> sa;
    /** 线程池 */
    private ExecutorService threasPools;

    /** 当前运行的线程*/
    private TaskThread currentTaskThread;

    public DTaskManager(Context context){
        this.context = context;
    }

    /**
     * 开启一个新线程
     * @param task
     * @param tag
     * @return
     */
    public TaskThread runAsyncTask(DTaskListener task, int tag) {
        if (this.isDestroy) {
            Log.e("DBaseActivity", "Activity have Destroy Error");
            return null;
        }
        if (this.threadQueue == null) {
            this.threadQueue = new LinkedList<Thread>();
        }

        if (this.threasPools == null) {
            this.threasPools = Executors.newFixedThreadPool(10);
        }

        if (this.sa == null) {
            this.sa = new SparseArray<Thread>();
        }

        if (this.sa.get(tag) != null) {
            this.sa.get(tag).interrupt();
            this.threadQueue.remove(this.sa.get(tag));
        }

        task.onTaskStart(tag);
        TaskThread thread = new TaskThread(task, tag);
        WeakReference<Thread> weakThread = new WeakReference<Thread>(thread);
        this.sa.put(tag, weakThread.get());
        this.threadQueue.add(weakThread.get());
        if (!this.threasPools.isShutdown()) {
            // 任务开始
            this.threasPools.submit((Runnable) weakThread.get());
        }
        return (TaskThread) weakThread.get();
    }

    /**
     * 开启一个新线程
     * @param task
     * @param tag
     */
    public void newTask(DTaskListener task, int tag){
        currentTaskThread = runAsyncTask(task, tag);
    }

    /**
     * 销毁当前的线程任务
     */
    public void cancelCurrentTask(){
        if(null != currentTaskThread){
            currentTaskThread.setCancel(true);
            currentTaskThread = null;
        }
    }

    /**
     * 销毁所有线程
     */
    public void destroyAsync() {
        if (this.sa != null) {
            this.sa.clear();
        }
        if ((this.threadQueue != null) && (this.threadQueue.size() > 0)) {
            Iterator<Thread> threads = this.threadQueue.iterator();
            while (threads.hasNext()) {
                Thread thread = (Thread) threads.next();
                thread.interrupt();
                Log.d(super.getClass().getSimpleName(),"destroyAsync and interrupt all threads");
            }
            if ((this.threasPools != null) && (!this.threasPools.isShutdown()))
                this.threasPools.shutdown();
        }
    }

    /**
     * 销毁所有的线程
     */
    protected void onDestroy() {
        this.isDestroy = true;
        Thread thread = new Thread(new Runnable() {
            public void run() {
                destroyAsync();
            }
        });
        thread.start();
    }

    /**
     * 任务主线程
     * @author cheng
     */
    public class TaskThread extends Thread {
        private DTaskListener taskListener;
        private int tag;
        private boolean isCancel = false;

        public TaskThread(DTaskListener listener, int tag) {
            this.taskListener = listener;
            this.tag = tag;
        }

        @Override
        public void run() {
            final Object result = this.taskListener.onTaskLoading(this.tag);
            //if (!this.isCancel) {
            ((Activity) context).runOnUiThread(new Runnable() {
                public void run() {
                    TaskThread.this.taskListener.onTaskFinish(TaskThread.this.tag, result);
                }
            });
            //}
            sa.delete(this.tag);
            threadQueue.remove(this);
        }

        /**
         * 是否取消任务
         * @return
         */
        public boolean isCancel() {
            return this.isCancel;
        }

        /**
         * 取消任务
         * @param isCancel
         */
        public void setCancel(boolean isCancel) {
            this.isCancel = isCancel;
        }
    }
}
