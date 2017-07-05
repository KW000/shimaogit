package com.android;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.http.HttpRequestService;
import com.android.mylibrary.DBaseActivity;
import com.android.utils.WindowBarManage;
import com.android.views.dialog.Loading;

import java.util.HashMap;

/**
 * 作者：flyframe on 2017/3/8 10:54
 * 邮箱：jxsm6@163.com
 */
public abstract class BaseActivity extends DBaseActivity {

    public HttpRequestService httpRequestService = HttpRequestService.getInstance();
    public HashMap<String, String> params;
    private Loading loading = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_root);
        this.params = new HashMap<String, String>();
        if (isMain())
            WindowBarManage.initWindow(this,false,(FrameLayout) this.findViewById(R.id.fl_title_bar));
        else
            WindowBarManage.initWindow(this,true,(FrameLayout) this.findViewById(R.id.fl_title_bar));
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    /**
     * 设置布局
     *
     * @param layoutResID
     */
    public void setContainerView(int layoutResID) {
        RelativeLayout container = getViewById(R.id.rl_root_container);
        getLayoutInflater().inflate(layoutResID, container);
        initData();
        initView();
    }

    /**
     * 隐藏标题栏布局
     */
    public void hideTitleLayout() {
        getViewById(R.id.fl_title_bar).setVisibility(View.GONE);
    }

    /**
     * 设置标题
     *
     * @param int
     */
    public void setHTitle(int resId) {
        ((TextView) getViewById(R.id.tv_title_text)).setText(resId);
    }

    /**
     * 设置标题
     *
     * @param String
     */
    public void setHTitle(String title) {
        ((TextView) getViewById(R.id.tv_title_text)).setText(title);
    }

    /** 标题栏左侧Button点击事件 */
    public void onLeftClick(View view) {
        this.finish();
    }

    /** 标题栏右侧Button点击事件 */
    public void onRightClick(View view) {
    }

    /** 标题栏中间view点击事件 */
    public void onCenterClick(View view) {
    }

    public void hideLeft() {
        getViewById(R.id.btn_left).setVisibility(View.GONE);
    }

    /**
     * 设置标题栏左侧Text
     *
     * @param text
     */
    public void setLeftText(String text) {
        final Button button = getViewById(R.id.btn_left);
        button.setVisibility(View.VISIBLE);
        button.setText(text);
    }

    /**
     * 设置标题栏左侧Text
     *
     * @param resId
     */
    public void setLeftText(int resId) {
        final Button button = getViewById(R.id.btn_left);
        button.setVisibility(View.VISIBLE);
        button.setText(resId);
    }

    /**
     * 设置标题栏左侧图标
     *
     * @param resId
     */
    public void setLeftDrawable(int resId) {
        final Button button = getViewById(R.id.btn_left);
        button.setVisibility(View.VISIBLE);
        button.setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0);
    }

    /**
     * 设置标题栏右侧Text
     *
     * @param text
     */
    public void setRightText(String text) {
        final Button button = getViewById(R.id.btn_right);
        button.setVisibility(View.VISIBLE);
        button.setText(text);
    }

    /**
     * 设置标题栏右侧Text
     *
     * @param resId
     */
    public void setRightText(int resId) {
        final Button button = getViewById(R.id.btn_right);
        button.setVisibility(View.VISIBLE);
        button.setText(resId);
    }

    public void setRightEnable(boolean isEnable) {
        final Button button = getViewById(R.id.btn_right);
        button.setEnabled(isEnable);
    }

    /**
     * 设置标题栏右侧图标
     *
     * @param resId
     */
    public void setRightDrawable(int resId) {
        final Button button = getViewById(R.id.btn_right);
        button.setVisibility(View.VISIBLE);
        @SuppressWarnings("deprecation")
        Drawable drawable = getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        button.setCompoundDrawables(null, null, drawable, null);

    }

    public void showLoading(){
        if (loading == null){
            loading = new Loading(this);
            loading.show();
        }else {
            if (!loading.isShowing()){
                loading.show();
            }
            return;
        }
    }

    /**
     * 关闭对话框，并结束当前运行的线程
     */
    public void endLoading() {
        if (null != loading && loading.isShowing()) {
            loading.dismiss();
        }
    }

    /**
     * 是否进入的是住页面
     * @return
     */
    public boolean isMain(){
        if (this instanceof MainActivity) {
            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        endLoading();
        loading = null;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
