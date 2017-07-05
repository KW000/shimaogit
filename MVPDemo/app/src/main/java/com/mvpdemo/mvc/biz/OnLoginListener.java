package com.mvpdemo.mvc.biz;

import com.mvpdemo.mvc.bean.User;

/**
 * 作者：flyframe on 2017/4/12 15:25
 * 邮箱：jxsm6@163.com
 */
public interface OnLoginListener {
    void loginSuccess(User user);
    void loginFailed();
}
