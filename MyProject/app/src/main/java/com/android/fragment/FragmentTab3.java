package com.android.fragment;

import android.os.Build;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.R;
import com.android.utils.WindowBarManage;

/**
 * 作者：flyframe on 2017/3/8 16:09
 * 邮箱：jxsm6@163.com
 */
public class FragmentTab3 extends BaseFragment {

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_tab3;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initView(View view) {
        LinearLayout line = getViewById(view, R.id.lienar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 优化状态栏与当前页面风格一致
            WindowBarManage.setViewHeight(getActivity(),true,line);
        }
    }


}
