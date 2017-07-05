package com.android.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 作者：flyframe on 2017/4/11 14:59
 * 邮箱：jxsm6@163.com
 */
public class HttpUtils {

    /**
     * 检查当前网络是否可用
     * @param context
     * @return
     */
    public static final boolean isNetworkAvailable(Context context){
        if (context == null){
            throw  new NullPointerException();
        }
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
        if (info != null) {
            for (int i = 0; i < info.length; ++i) {
                if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 检测wifi是否可用
     *
     * @param context
     * @return
     */
    public static final boolean isWifiOnline(Context context) {
        if (context == null) {
            throw new NullPointerException();
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getNetworkInfo(1);
        if (info == null) {
            return false;
        }
        return info.isAvailable();
    }

    /**
     * 检测手机移动网络是否可用
     *
     * @param context
     * @return
     */
    public static final boolean isMobileNetworkOnline(Context context) {
        if (context == null) {
            throw new NullPointerException();
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getNetworkInfo(0);
        if (info == null) {
            return false;
        }
        return info.isAvailable();
    }

    /**
     * 获取网络信息
     * @param context
     * @return
     */
    public static final NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo;
    }

}
