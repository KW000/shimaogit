package com.android.mylibrary.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 作者：flyframe on 2017/3/23 13:38
 * 邮箱：jxsm6@163.com
 */
public class HttpUtils {
    /**
     * 打开网络设置界面
     * @param context
     */
    public static void openWirelessSetting(Context context) {
        Intent intent = new Intent();
        ComponentName cm = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        context.startActivity(intent);
    }

    /**
     * 检测网络是否可用
     *
     * @param context
     * @return
     */
    public static final boolean isNetworkOnline(Context context) {
        if (context == null) {
            throw new NullPointerException();
        }
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivity == null) {
            return false;
        }
        NetworkInfo[] info = connectivity.getAllNetworkInfo();
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
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService("connectivity");
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
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService("connectivity");
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
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo;
    }

    /**
     * 获取Mac地址
     * @param context
     * @return
     */
    public String getMacAddress(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

    /**
     * 获取当前的ipv4地址
     * @return
     */
    public String getIpV4Address() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("WifiPreference IpAddress", ex.toString());
        }
        return null;
    }
}
