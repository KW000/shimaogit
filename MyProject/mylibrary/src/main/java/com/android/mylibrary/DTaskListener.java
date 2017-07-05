package com.android.mylibrary;

/**
 * 作者：flyframe on 2017/3/1 15:18
 * 邮箱：jxsm6@163.com
 * des 线程任务回调接口
 */
public interface DTaskListener {
    /**
     * 任务开始
     * @param paramInt 任务标记tag
     */
    public abstract void onTaskStart(int tag);
    /**
     * 任务进行中
     * @param paramInt 任务标记tag
     * @return 任务执行完成后返回的对象
     */
    public abstract Object onTaskLoading(int tag);
    /**
     * 任务结束
     * @param paramInt 任务标记tag
     * @param paramObject 返回对象
     */
    public abstract void onTaskFinish(int tag, Object object);
}
