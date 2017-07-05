package com.android.mylibrary;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.android.mylibrary.pulltorefresh.PullToRefreshAdapterViewBase;

/**
 * 作者：flyframe on 2017/3/1 15:14
 * 邮箱：jxsm6@163.com
 */
public abstract class DBaseActivity extends FragmentActivity {
    /**日志Tag标记*/
    protected final String TAG = getClass().getName();

    /**上下文对象*/
    public Context context;
    /**线程管理工具*/
    protected DTaskManager taskManager = new DTaskManager(this);
    /**Fragment管理工具*/
    protected FragmentManager fgManager = getSupportFragmentManager();
    /**ImageLoad下载状态控制*/
    protected final DLoaderStateManager lsManager = new DLoaderStateManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //防截屏代码
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        this.context = this;
    }

    /**初始化视图*/
    protected abstract void initView();
    /**初始化数据*/
    protected abstract void initData();

    /**
     * 根据Id获取View
     * @param id
     * @return
     */
    protected <T extends View> T getViewById(int id) {
        return (T) findViewById(id);
    }

    @SuppressWarnings("rawtypes")
    public void setPullRefreshView(PullToRefreshAdapterViewBase puAdapterViewBase){
        if(null != lsManager){
            lsManager.setPullRefreshView(puAdapterViewBase);
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if(null != lsManager){
            lsManager.applyScrollListener();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
        if(null != lsManager){
            lsManager.onSaveInstanceState(outState);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onRestoreInstanceState(savedInstanceState);
        if(null != lsManager){
            lsManager.onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if(null != taskManager){
            taskManager.onDestroy();
            taskManager = null;
        }
    }
}
