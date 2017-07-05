package com.android.mylibrary;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.mylibrary.pulltorefresh.PullToRefreshAdapterViewBase;


/**
 * 作者：flyframe on 2017/3/2 16:26
 * 邮箱：jxsm6@163.com
 */
public abstract class DBaseFragment extends Fragment {

    /**日志Tag标记*/
    protected final String TAG = getClass().getName();

    /**上下文对象*/
    public Context context = getActivity();
    /**线程管理工具*/
    protected DTaskManager taskManager = null;
    /**Fragment管理工具*/
    public FragmentManager fgChildManager = null;
    /**ImageLoad下载状态控制*/
    protected final DLoaderStateManager lsManager = new DLoaderStateManager();
    private View view;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        if (null == view) {
            view = inflater.inflate(setContentLayout(), container, false);
            view.setClickable(true);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.context = getActivity();
        taskManager = new DTaskManager(context);
        fgChildManager = getChildFragmentManager();
        initData();
        initView(view);
    }

    protected abstract void initData();
    protected abstract void initView(View view);
    protected abstract int setContentLayout();
    /**
     * 根据Id获取View
     * @param id
     * @return
     */
    protected <T extends View> T getViewById(View view, int id) {
        return (T) view.findViewById(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(null != taskManager){
            taskManager.onDestroy();
            taskManager = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(null != lsManager){
            lsManager.applyScrollListener();
        }
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(null != lsManager){
            lsManager.onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(null != lsManager){
            lsManager.onSaveInstanceState(outState);
        }
    }

    @SuppressWarnings("rawtypes")
    public void setPullRefreshView(PullToRefreshAdapterViewBase puAdapterViewBase){
        if(null != lsManager){
            lsManager.setPullRefreshView(puAdapterViewBase);
        }
    }
}
