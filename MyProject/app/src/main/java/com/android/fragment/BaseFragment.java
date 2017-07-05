package com.android.fragment;

import android.view.View;

import com.android.http.HttpRequestService;
import com.android.mylibrary.DBaseFragment;
import com.android.views.dialog.Loading;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：flyframe on 2017/3/8 14:00
 * 邮箱：jxsm6@163.com
 */
public class BaseFragment extends DBaseFragment {

    public Map<String, String> params = new HashMap<String, String>();
    public HttpRequestService httpRequestService = HttpRequestService.getInstance();
    private Loading loading = null;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected int setContentLayout() {
        return 0;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        endLoading();
        loading = null;
    }

    public void showLoading(){
        if (loading == null){
            loading = new Loading(context);
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
     * Fragment是否已经显示
     *
     * @return
     */
    public boolean isFragmentShow() {
        return isVisible() && !isHidden();
    }
}
