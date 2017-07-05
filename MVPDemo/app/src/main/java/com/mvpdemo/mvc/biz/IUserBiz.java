package com.mvpdemo.mvc.biz;

/**
 * 作者：flyframe on 2017/4/12 15:24
 * 邮箱：jxsm6@163.com
 */
public interface IUserBiz {

    public void login(String username, String password, OnLoginListener loginListener);
}
