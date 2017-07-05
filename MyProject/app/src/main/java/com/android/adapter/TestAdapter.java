package com.android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：flyframe on 2017/4/11 16:47
 * 邮箱：jxsm6@163.com
 */
public class TestAdapter<T> extends DBaseUIAdapter<T>{

    public TestAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup, ViewHolder viewHolder) {
        /**
         * 这里直接获取页面的控件即可
         */
        return paramView;
    }

    @Override
    public int setItemLayoutRes() {
        return 0;
    }

}
