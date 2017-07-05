package com.android.swipe;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.R;
import java.util.List;

/**
 * desc
 * Author:shimao
 * Date:2017.04.25 14:52
 */
public class SwipeAdapter extends BaseSwipListAdapter{

    private List<ApplicationInfo> mAppList;

    private Context context;

    public SwipeAdapter(List<ApplicationInfo> mlist, Context context) {
        this.mAppList = mlist;
        this.context = context;
    }

    @Override
    public boolean getSwipEnableByPosition(int position) {
//        if(position % 2 == 0){
//            return false;
//        }
        return true;
    }

    @Override
    public int getCount() {
        return mAppList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAppList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_simpe,null);
            holder = new ViewHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.tv = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        ApplicationInfo item = mAppList.get(position);
        /**
         * 获取Apk的图标
         */
        holder.iv.setImageDrawable(item.loadIcon(context.getPackageManager()));
        /**
         * 获取Apk的名称
         */
        holder.tv.setText(item.loadLabel(context.getPackageManager()));
        return convertView;
    }

    static class ViewHolder{
        TextView tv;
        ImageView iv;
    }

}
