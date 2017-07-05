package com.android.fragment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.R;
import com.android.adapter.DBaseUIAdapter;

import java.util.ArrayList;

/**
 * desc
 * Author:shimao
 * Date:2017.04.27 10:59
 */
public class Tab1Tab4ListAdapter<T> extends DBaseUIAdapter<T>{

    public Tab1Tab4ListAdapter(Context context, ArrayList<T> tList) {
        super(context, tList);
        this.tList = tList;
    }

    @Override
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup, DBaseUIAdapter<T>.ViewHolder viewHolder) {
        TextView tv = viewHolder.obtainView(paramView,R.id.tab1_tab4_itme);
        tv.setText(tList.get(paramInt).toString());
        return paramView;
    }

    @Override
    public int setItemLayoutRes() {
        return R.layout.tab1_tab4_list_item;
    }
}
