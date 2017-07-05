package com.android.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.utils.ResourceUtils;
import java.util.ArrayList;

/**
 * 作者：flyframe on 2017/4/11 16:41
 * 邮箱：jxsm6@163.com
 */
public abstract class DBaseUIAdapter<T> extends DBaseAdapter<T>{

    public DBaseUIAdapter(Context context) {
        super(context);
    }

    public DBaseUIAdapter(Context context, ArrayList<T> tList) {
        super(context, tList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(setItemLayoutRes(), null);
            holder = new ViewHolder();
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return getView(position, convertView, parent, holder);
    }

    public abstract View getView(int paramInt, View paramView,ViewGroup paramViewGroup,DBaseUIAdapter<T>.ViewHolder viewHolder);

    public abstract int setItemLayoutRes();

    @SuppressWarnings("unchecked")
    protected class ViewHolder {
        private SparseArray<View> views = new SparseArray<View>();
        protected ViewHolder() {
        }

        /**
         *
         * @param convertView
         * @param resId
         * @return
         */
        public <E extends View> E obtainView(View convertView, int resId) {
            View v = (View) this.views.get(resId);
            if (null == v) {
                //DLog.d("obtainView","添加"+resId);
                v = convertView.findViewById(resId);
                this.views.put(resId, v);
            }
            //DLog.d("obtainView","取得"+resId);
            return (E) v;
        }

        public <E extends View> E obtainView(View convertView, String resIdstr) {
            int resId = ResourceUtils.getIDResourceId(DBaseUIAdapter.this.context, resIdstr);
            View v = (View) this.views.get(resId);
            if (null == v) {
                v = convertView.findViewById(resId);
                this.views.put(resId, v);
            }
            return (E) v;
        }
    }

}
