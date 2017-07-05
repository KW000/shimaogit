package com.android.fragment;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import com.android.R;
import com.android.adapter.FragmentAdapter;
import com.android.fragment.tab2.Tab2FragmentTab1;
import com.android.fragment.tab2.Tab2FragmentTab2;
import com.android.fragment.tab2.Tab2FragmentTab3;
import com.android.fragment.tab2.Tab2FragmentTab4;
import com.android.fragment.tab2.Tab2FragmentTab5;
import com.android.fragment.tab2.Tab2FragmentTab6;
import com.android.utils.WindowBarManage;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：flyframe on 2017/3/8 16:09
 * 邮箱：jxsm6@163.com
 */
public class FragmentTab2 extends BaseFragment {

    private RelativeLayout relab;
    private RadioGroup group;
    private RadioButton rb1,rb2,rb3,rb4,rb5,rb6;
    private ViewPager viewPager;
    /**
     * ViewPager的当前选中页
     */
    private int currentIndex;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;
    private BaseFragment tab1,tab2,tab3,tab4,tab5,tab6;


    @Override
    protected int setContentLayout() {
        return R.layout.fragment_tab2;
    }

    @Override
    protected void initData() {
        tab1 = new Tab2FragmentTab1();
        tab2 = new Tab2FragmentTab2();
        tab3 = new Tab2FragmentTab3();
        tab4 = new Tab2FragmentTab4();
        tab5 = new Tab2FragmentTab5();
        tab6 = new Tab2FragmentTab6();
        mFragmentList.add(tab1);
        mFragmentList.add(tab2);
        mFragmentList.add(tab3);
        mFragmentList.add(tab4);
        mFragmentList.add(tab5);
        mFragmentList.add(tab6);
    }

    @Override
    protected void initView(View view) {
        relab = getViewById(view,R.id.relab);
        relab.setAlpha(0.8f);
        RelativeLayout line = getViewById(view, R.id.rela);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 优化状态栏与当前页面风格一致
            WindowBarManage.setViewHeight(getActivity(),true,line);
        }
        viewPager = getViewById(view,R.id.view_pager);
        group = getViewById(view,R.id.group);
        rb1 = getViewById(view,R.id.tab2_rb1);
        rb2 = getViewById(view,R.id.tab2_rb2);
        rb3 = getViewById(view,R.id.tab2_rb3);
        rb4 = getViewById(view,R.id.tab2_rb4);
        rb5 = getViewById(view,R.id.tab2_rb5);
        rb6 = getViewById(view,R.id.tab2_rb6);
        mFragmentAdapter = new FragmentAdapter(this.getChildFragmentManager(), mFragmentList);
        viewPager.setAdapter(mFragmentAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
                viewPager.setCurrentItem(currentIndex);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.tab2_rb1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.tab2_rb2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.tab2_rb3:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.tab2_rb4:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.tab2_rb5:
                        viewPager.setCurrentItem(4);
                        break;
                    case R.id.tab2_rb6:
                        viewPager.setCurrentItem(5);
                        break;
                }
            }
        });
    }

}


