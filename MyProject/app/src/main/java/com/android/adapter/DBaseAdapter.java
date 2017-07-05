package com.android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：flyframe on 2017/4/11 16:27
 * 邮箱：jxsm6@163.com
 * desc 基础适配器
 */
public abstract class DBaseAdapter<T> extends BaseAdapter{

    protected List<T> tList;
    protected Context context;
    private AbsListView listView;

    protected Context getContext(){
        return this.context;
    }

    public DBaseAdapter(Context context) {
        this(context,new ArrayList<T>());
    }

    public DBaseAdapter(Context context, ArrayList<T> tList) {
        this.tList = tList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.tList.size();
    }

    @Override
    public T getItem(int position) {
        return this.tList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public void setListView(AbsListView listView) {
        this.listView = listView;
    }

}
