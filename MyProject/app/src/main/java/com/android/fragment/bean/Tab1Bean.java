package com.android.fragment.bean;

/**
 * 作者：flyframe on 2017/4/6 14:37
 * 邮箱：jxsm6@163.com
 */
public class Tab1Bean extends DBean {

    public Tab1Bean(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    private String title;
    private String desc;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
