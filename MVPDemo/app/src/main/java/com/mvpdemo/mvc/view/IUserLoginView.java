package com.mvpdemo.mvc.view;

import com.mvpdemo.mvc.bean.User;

/**
 * 作者：flyframe on 2017/4/12 15:27
 * 邮箱：jxsm6@163.com
 */
public interface IUserLoginView {

    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();
}
