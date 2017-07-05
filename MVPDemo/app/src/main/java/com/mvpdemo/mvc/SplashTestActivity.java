package com.mvpdemo.mvc;

import com.umeng.message.inapp.InAppMessageManager;
import com.umeng.message.inapp.UmengSplashMessageActivity;

/**
 * 作者：flyframe on 2017/4/13 15:33
 * 邮箱：jxsm6@163.com
 */
public class SplashTestActivity extends UmengSplashMessageActivity {

    @Override
    public boolean onCustomPretreatment() {
        InAppMessageManager mInAppMessageManager = InAppMessageManager.getInstance(this);
        mInAppMessageManager.setInAppMsgDebugMode(true);
        mInAppMessageManager.setMainActivityPath("com.mvpdemo.mvp.MainActivity");
        return super.onCustomPretreatment();
    }
}
