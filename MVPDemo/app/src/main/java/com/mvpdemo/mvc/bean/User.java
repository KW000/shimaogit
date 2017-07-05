package com.mvpdemo.mvc.bean;

/**
 * 作者：flyframe on 2017/4/12 15:22
 * 邮箱：jxsm6@163.com
 */
public class User {

    private String name;
    private String pwd;

    public User() {
        super();
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
