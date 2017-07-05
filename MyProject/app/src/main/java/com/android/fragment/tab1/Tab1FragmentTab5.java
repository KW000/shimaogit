package com.android.fragment.tab1;

import android.view.View;
import android.widget.LinearLayout;
import com.android.R;
import com.android.fragment.BaseFragment;
import com.android.pullview.PullToZoomScrollViewEx;
import com.android.utils.DAppInfo;

/**
 * 作者：flyframe on 2017/3/10 14:11
 * 邮箱：jxsm6@163.com
 */
public class Tab1FragmentTab5 extends BaseFragment{

    private PullToZoomScrollViewEx scrollViewEx;

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected int setContentLayout() {
        return R.layout.frag_tab1_tab5;
    }

    @Override
    protected void initView(View view) {
        scrollViewEx = getViewById(view,R.id.scroll_view);
        int width = DAppInfo.width;
        LinearLayout.LayoutParams  lp = new LinearLayout.LayoutParams(width,(int)(9.0f*width/16.0f));
        scrollViewEx.setHeaderLayoutParams(lp);
    }
}
