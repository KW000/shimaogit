package com.mvpdemo.mvc.biz;

import com.mvpdemo.mvc.bean.User;

/**
 * 作者：flyframe on 2017/4/12 15:25
 * 邮箱：jxsm6@163.com
 */
public class UserBiz implements IUserBiz{
    @Override
    public void login(final String username, final String password, final OnLoginListener loginListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if ("123".equals(username) && "123".equals(password)){
                    User user = new User();
                    user.setName(username);
                    user.setPwd(password);
                    loginListener.loginSuccess(user);
                }else {
                    loginListener.loginFailed();
                }
            }
        }).start();
    }
}
