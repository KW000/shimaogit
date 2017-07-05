package com.android.fragment;

import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.R;
import com.android.mylibrary.transform.GlideCircleTransform;
import com.android.mylibrary.transform.GlideRoundTransform;
import com.android.mylibrary.views.RoundImageView;
import com.android.utils.WindowBarManage;
import com.bumptech.glide.Glide;

/**
 * 作者：flyframe on 2017/3/8 16:09
 * 邮箱：jxsm6@163.com
 */
public class FragmentTab4 extends BaseFragment {

    private RoundImageView img;
    private ImageView iv,ivr;

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_tab4;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initView(View view) {
        RelativeLayout line = getViewById(view, R.id.tab4_top);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 优化状态栏与当前页面风格一致
            WindowBarManage.setViewHeight(getActivity(),true,line);
        }
        img = getViewById(view,R.id.img);
        iv = getViewById(view,R.id.iv);
        ivr = getViewById(view,R.id.ivr);
        // 加载圆形图片
        Glide.with(context).load(R.drawable.gril).transform(new GlideCircleTransform(context)).into(iv);
        // 加载圆角图片
        Glide.with(context).load(R.drawable.gril).transform(new GlideRoundTransform(context,10)).into(ivr);
    }
}
