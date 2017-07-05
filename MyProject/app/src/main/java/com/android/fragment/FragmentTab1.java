package com.android.fragment;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.R;
import com.android.adapter.FragmentAdapter;
import com.android.fragment.tab1.Tab1FragmentTab1;
import com.android.fragment.tab1.Tab1FragmentTab2;
import com.android.fragment.tab1.Tab1FragmentTab3;
import com.android.fragment.tab1.Tab1FragmentTab4;
import com.android.fragment.tab1.Tab1FragmentTab5;
import com.android.fragment.tab1.Tab1FragmentTab6;
import com.android.utils.DisplayUtils;
import com.android.utils.WindowBarManage;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：flyframe on 2017/3/8 16:09
 * 邮箱：jxsm6@163.com
 */
public class FragmentTab1 extends BaseFragment {

    private RelativeLayout top;
    private TextView ttt;
    /**
     * ViewPager的当前选中页
     */
    private int currentIndex;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;
    private ViewPager mPageVp;
    private BaseFragment tab1,tab2,tab3,tab4,tab5,tab6;

    private RadioGroup group;
    private RadioButton rb1,rb2,rb3,rb4,rb5,rb6;


    @Override
    protected int setContentLayout() {
        return R.layout.fragment_tab1;
    }

    @Override
    protected void initData() {
        tab1 = new Tab1FragmentTab1();
        tab2 = new Tab1FragmentTab2();
        tab3 = new Tab1FragmentTab3();
        tab4 = new Tab1FragmentTab4();
        tab5 = new Tab1FragmentTab5();
        tab6 = new Tab1FragmentTab6();
        mFragmentList.add(tab1);
        mFragmentList.add(tab2);
        mFragmentList.add(tab3);
        mFragmentList.add(tab4);
        mFragmentList.add(tab5);
        mFragmentList.add(tab6);
    }

    @Override
    protected void initView(View view) {
        mPageVp = getViewById(view,R.id.view_pager);
        group = getViewById(view,R.id.radio);
        rb1 = getViewById(view,R.id.tab1_rb1);
        rb2 = getViewById(view,R.id.tab1_rb2);
        rb3 = getViewById(view,R.id.tab1_rb3);
        rb4 = getViewById(view,R.id.tab1_rb4);
        rb5 = getViewById(view,R.id.tab1_rb5);
        rb6 = getViewById(view,R.id.tab1_rb6);
        top = getViewById(view,R.id.top);
        ttt = getViewById(view,R.id.ttt);
        ttt.setAlpha(0.8f);
        setAdHeight(top);
        /**fragment 嵌套时最好用getChildFragmentManager()方法，否则会数据丢失不限时值fragment*/
        mFragmentAdapter = new FragmentAdapter(this.getChildFragmentManager(), mFragmentList);
        mPageVp.setAdapter(mFragmentAdapter);
        mPageVp.setCurrentItem(0);
        mPageVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentIndex = position;
                switch(position){
                    case 0:
                        rb1.setChecked(true);
                        break;
                    case 1:
                        rb2.setChecked(true);
                        break;
                    case 2:
                        rb3.setChecked(true);
                        break;
                    case 3:
                        rb4.setChecked(true);
                        break;
                    case 4:
                        rb5.setChecked(true);
                        break;
                    case 5:
                        rb6.setChecked(true);
                        break;
                }
                mPageVp.setCurrentItem(currentIndex);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.tab1_rb1:
                        mPageVp.setCurrentItem(0);
                        break;
                    case R.id.tab1_rb2:
                        mPageVp.setCurrentItem(1);
                        break;
                    case R.id.tab1_rb3:
                        mPageVp.setCurrentItem(2);
                        break;
                    case R.id.tab1_rb4:
                        mPageVp.setCurrentItem(3);
                        break;
                    case R.id.tab1_rb5:
                        mPageVp.setCurrentItem(4);
                        break;
                    case R.id.tab1_rb6:
                        mPageVp.setCurrentItem(5);
                        break;
                }
            }
        });
    }


    private void setAdHeight(View view) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int statusBarHeight = WindowBarManage.getStatusHeight(getActivity());
            lp.height = DisplayUtils.dip2px(this.getActivity(), 48f) + statusBarHeight;
            view.setPadding(0, statusBarHeight, 0, 0);
            view.setLayoutParams(lp);
        }
    }

}
