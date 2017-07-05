package com.android.fragment.tab1;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.R;
import com.android.fragment.BaseFragment;
import com.android.fragment.adapter.RecyclerViewAdapter;
import com.android.fragment.bean.Tab1Bean;
import com.android.interfaces.OnItemClickListener;
import com.handmark.pulltorefresh.library.PtrRecyclerView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 作者：flyframe on 2017/3/10 14:07
 * 邮箱：jxsm6@163.com
 */
public class Tab1FragmentTab1 extends BaseFragment implements PullToRefreshBase.OnRefreshListener2<RecyclerView>{

    private PtrRecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private List<Tab1Bean> mData;


    @Override
    protected void initData() {
        mData = new ArrayList<>();
        mData.add(new Tab1Bean("测试头1","测试内容1"));
        mData.add(new Tab1Bean("测试头2","测试内容2"));
        mData.add(new Tab1Bean("测试头3","测试内容3"));
        mData.add(new Tab1Bean("测试头4","测试内容4"));
        mData.add(new Tab1Bean("测试头5","测试内容5"));
    }

    @Override
    protected void initView(View view) {
        recyclerView = getViewById(view,R.id.recycler);
        adapter = new RecyclerViewAdapter(mData);
        text();
        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        GridLayoutManager manager1 = new GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        recyclerView.setOnRefreshListener(this);
        adapter.setmOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(context,mData.get(position).getTitle(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int setContentLayout() {
        return R.layout.frag_tab1_tab1;
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.onRefreshComplete();
                mData.clear();
                mData.add(new Tab1Bean("测试头1","测试内容1"));
                mData.add(new Tab1Bean("测试头2","测试内容2"));
                mData.add(new Tab1Bean("测试头3","测试内容3"));
                mData.add(new Tab1Bean("测试头4","测试内容4"));
                mData.add(new Tab1Bean("测试头5","测试内容5"));
                adapter.notifyDataSetChanged();
            }
        }, 1000);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.onRefreshComplete();
                mData.add(new Tab1Bean("测试头6","测试内容6"));
                mData.add(new Tab1Bean("测试头7","测试内容7"));
                mData.add(new Tab1Bean("测试头8","测试内容8"));
                mData.add(new Tab1Bean("测试头9","测试内容9"));
                mData.add(new Tab1Bean("测试头10","测试内容10"));
                adapter.notifyItemInserted(mData.size() - 1);
                //adapter.notifyDataSetChanged();
            }
        }, 1000);
    }

    private void text(){
        params.clear();
        httpRequestService.doRequestData(context, "https://www.2345.com/?ientab", "", params, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("onError","onError");
            }

            @Override
            public void onResponse(String response, int id) {

                Log.e("onResponse","========onResponse=====");
            }
        });
    }
}
