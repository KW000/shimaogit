package com.android.mylibrary;

import android.os.Bundle;

import com.android.mylibrary.pulltorefresh.PullToRefreshAdapterViewBase;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;


/**
 * 作者：flyframe on 2017/3/1 15:21
 * 邮箱：jxsm6@163.com
 */
public class DLoaderStateManager {
    protected static final String STATE_PAUSE_ON_SCROLL = "STATE_PAUSE_ON_SCROLL";
    protected static final String STATE_PAUSE_ON_FLING = "STATE_PAUSE_ON_FLING";

    protected boolean pauseOnScroll = false;
    protected boolean pauseOnFling = true;

    protected PullToRefreshAdapterViewBase pullRefreshBase;

    public void setPullRefreshView(PullToRefreshAdapterViewBase puAdapterViewBase){
        this.pullRefreshBase = puAdapterViewBase;
    }

    public PullToRefreshAdapterViewBase getPullRefreshView(){
        return this.pullRefreshBase;
    }

    /**
     * 保存状态
     * @param outState
     */
    public void onSaveInstanceState(Bundle outState) {
        if(null != outState){
            outState.putBoolean(STATE_PAUSE_ON_SCROLL, pauseOnScroll);
            outState.putBoolean(STATE_PAUSE_ON_FLING, pauseOnFling);
        }
    }

    /**
     * 恢复状态
     * @param savedInstanceState
     */
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        if(null != savedInstanceState){
            pauseOnScroll = savedInstanceState.getBoolean(STATE_PAUSE_ON_SCROLL, false);
            pauseOnFling = savedInstanceState.getBoolean(STATE_PAUSE_ON_FLING, true);
        }
    }

    /**
     * 恢复下载
     */
    public void onResume() {
        applyScrollListener();
    }

    /**
     * 监听滚动
     */
    public void applyScrollListener() {
        if(null != pullRefreshBase){
            pullRefreshBase.setOnPauseScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), pauseOnScroll, pauseOnFling));
        }
    }
}
