package com.android.views.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import com.android.R;

/**
 * 作者：flyframe on 2017/4/5 09:57
 * 邮箱：jxsm6@163.com
 */
public class Loading extends Dialog{

    private ImageView imageView;
    private AnimationDrawable animationDrawable;

    public Loading(Context context) {
        this(context,R.style.StyleDialog);
    }

    public Loading(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    private void init(Context context){
        setContentView(R.layout.loading);
        setCanceledOnTouchOutside(false);
        /**设置黑暗度*/
        getWindow().getAttributes().dimAmount = 0f;
        getWindow().getAttributes().gravity = Gravity.CENTER;
        imageView = (ImageView) findViewById(R.id.loading);
        animationDrawable = (AnimationDrawable) imageView.getBackground();
        if (null != animationDrawable){
            animationDrawable.start();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus && isShowing() && null != animationDrawable && !animationDrawable.isRunning()) {
            animationDrawable.start();
        }
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void dismiss() {
        if (null != animationDrawable && animationDrawable.isRunning()) {
            animationDrawable.stop();
        }
        super.dismiss();
    }
}
