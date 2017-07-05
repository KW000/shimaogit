package com.mvpdemo.mvc.presenter;

import android.os.Handler;
import com.mvpdemo.mvc.bean.User;
import com.mvpdemo.mvc.biz.IUserBiz;
import com.mvpdemo.mvc.biz.OnLoginListener;
import com.mvpdemo.mvc.biz.UserBiz;
import com.mvpdemo.mvc.view.IUserLoginView;

/**
 * 作者：flyframe on 2017/4/12 15:48
 * 邮箱：jxsm6@163.com
 */
public class UserLoginPresenter {

    private IUserBiz userBiz;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
    }

    public void login() {
        userLoginView.showLoading();
        userBiz.login(userLoginView.getUserName(), userLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.toMainActivity(user);
                        userLoginView.hideLoading();
                    }
                });

            }

            @Override
            public void loginFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.showFailedError();
                        userLoginView.hideLoading();
                    }
                });

            }
        });
    }

    public void clear() {
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }

}
