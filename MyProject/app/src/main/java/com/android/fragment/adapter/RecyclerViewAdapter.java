package com.android.fragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.R;
import com.android.fragment.bean.DBean;
import com.android.fragment.bean.Tab1Bean;
import com.android.interfaces.OnItemClickListener;

import java.util.List;

/**
 * 作者：flyframe on 2017/4/6 14:09
 * 邮箱：jxsm6@163.com
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Viewholder> {

    private List<Tab1Bean> mData;

    public RecyclerViewAdapter(List<Tab1Bean> mData) {
        this.mData = mData;
    }

    @Override public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);

        return new Viewholder(view);
    }

    @Override public void onBindViewHolder(final Viewholder holder, final int position) {
        holder.text.setText(mData.get(position).getTitle());
        holder.textv.setText(mData.get(position).getDesc());
        if (mOnItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.item,position);
                }
            });
        }
    }

    @Override public int getItemCount() {
        return mData.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {

        private RelativeLayout item;
        private TextView text;
        private TextView textv;

        public Viewholder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.textView2);
            textv = (TextView) itemView.findViewById(R.id.textView3);
            item = (RelativeLayout) itemView.findViewById(R.id.view);
        }
    }

    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

}
