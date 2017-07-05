package com.android.fragment.tab2;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.R;
import com.android.fragment.BaseFragment;

/**
 * 作者：flyframe on 2017/3/10 14:07
 * 邮箱：jxsm6@163.com
 */
public class Tab2FragmentTab1 extends BaseFragment implements View.OnClickListener{

    private Button button;

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initView(View view) {
        button = getViewById(view,R.id.btn);
        button.setOnClickListener(this);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.frag_tab2_tab1;
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn:
                Log.e("Tab2FragmentTab1","BUTTON");
                showLoading();
                break;
            case 2:
                break;
        }
    }
}
