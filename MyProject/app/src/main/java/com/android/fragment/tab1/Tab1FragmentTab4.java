package com.android.fragment.tab1;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;

import com.android.R;
import com.android.fragment.BaseFragment;
import com.android.fragment.adapter.Tab1Tab4ListAdapter;
import com.android.pullview.PullToZoomListViewEx;
import com.android.utils.DAppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：flyframe on 2017/3/10 14:11
 * 邮箱：jxsm6@163.com
 */
public class Tab1FragmentTab4 extends BaseFragment{

    private PullToZoomListViewEx listViewEx;

    private ArrayList<String> list = new ArrayList<>();

    private Tab1Tab4ListAdapter adapter;

    @Override
    protected void initData() {
        for (int i = 0; i < 20; i++) {
            list.add("text"+i);
        }
        adapter = new Tab1Tab4ListAdapter(context,list);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.frag_tab1_tab4;
    }

    @Override
    protected void initView(View view) {
        listViewEx = getViewById(view,R.id.list_view);
        listViewEx.setAdapter(adapter);
        int mScreenWidth = DAppInfo.width;
        AbsListView.LayoutParams localObject = new AbsListView.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
        listViewEx.setHeaderLayoutParams(localObject);
        listViewEx.setZoomEnabled(true);
        /**隐藏头部*/
        listViewEx.setHideHeader(false);
    }
}
