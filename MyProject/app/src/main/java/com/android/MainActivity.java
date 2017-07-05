package com.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.android.fragment.BaseFragment;
import com.android.fragment.FragmentTab1;
import com.android.fragment.FragmentTab2;
import com.android.fragment.FragmentTab3;
import com.android.fragment.FragmentTab4;

public class MainActivity extends BaseActivity{

    private BaseFragment fmTab1, fmTab2, fmTab3, fmTab4;
    /** 当前切换的tab索引 */
    private int currentIndex = R.id.rb_tab1;
    private boolean isRefresh = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        if (null != savedInstanceState) {
            currentIndex = savedInstanceState.getInt("currentIndex");
        }
        switchFragment(currentIndex,true);
    }

    @Override
    protected void initView() {
        RadioGroup group = getViewById(R.id.radio);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int id) {
                if (isRefresh) {
                    switchFragment(id, true);
                } else {
                    isRefresh = true;
                    switchFragment(id, false);
                }
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
    }

    /**
     * 切换Fragment
     * @param
     */
    public void switchFragment(int index, boolean isRefresh) {
        FragmentTransaction ft = fgManager.beginTransaction();
        hideAllFrament(ft);
        ft.commitAllowingStateLoss();
        ft = fgManager.beginTransaction();
        currentIndex = index;
        BaseFragment curFragment = null;
        switch (index) {
            case R.id.rb_tab1:
                // 首页
                if (null == fmTab1) {
                    fmTab1 = new FragmentTab1();
                    ft.add(R.id.fly_container, fmTab1);
                }
                curFragment = fmTab1;
                break;
            case R.id.rb_tab2:
                // 视频
                if (null == fmTab2) {
                    fmTab2 = new FragmentTab2();
                    ft.add(R.id.fly_container, fmTab2);
                }
                curFragment = fmTab2;
                break;

            case R.id.rb_tab3:
                // 关注
                if (null == fmTab3) {
                    fmTab3 = new FragmentTab3();
                    ft.add(R.id.fly_container, fmTab3);
                }
                curFragment = fmTab3;
                break;
            case R.id.rb_tab4:
                // 我的
                if (null == fmTab4) {
                    fmTab4 = new FragmentTab4();
                    ft.add(R.id.fly_container, fmTab4);
                }
                curFragment = fmTab4;
                break;
        }
        if (null != curFragment) {
            ft.show(curFragment).commitAllowingStateLoss();
        }
    }

    /**
     * 隐藏所有的fragment
     *
     * @param ft
     */
    private void hideAllFrament(FragmentTransaction ft) {
        if (null != fmTab1) {
            ft.hide(fmTab1);
        }

        if (null != fmTab2) {
            ft.hide(fmTab2);
        }

        if (null != fmTab3) {
            ft.hide(fmTab3);
        }
        if (null != fmTab4) {
            ft.hide(fmTab4);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (null != outState) {
            Log.d("onSaveInstanceState", currentIndex + "");
            outState.putInt("currentIndex", currentIndex);
        }
    }

    /**
     * 返回到指定 页面
     *
     * @param radionButtonId
     */
    public void gotoTab(int radionButtonId) {
        ((RadioButton) getViewById(radionButtonId)).setChecked(true);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if ((Intent.FLAG_ACTIVITY_CLEAR_TOP & intent.getFlags()) != 0) {
            if (null != intent) {
                int flag = intent.getIntExtra("IntentFlag", -1);
                switch (flag) {
                    case 0x01:
                        // 返回到指定页面
                        isRefresh = false;
                        gotoTab(R.id.rb_tab1);
                        break;
                    case 0x02:
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
