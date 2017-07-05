package com.android.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
/**
 * 作者：flyframe on 2017/3/9 16:17
 * 邮箱：jxsm6@163.com
 */
public class DAppInfo {

    private final static String TAG = "AppInfo";

    /**User-Agent*/
    public static String appUA = "android";
    /**平台类型*/
    public static String appPlatForm = "android";

    /**设备的id号 imei*/
    public static String deviceId = "";
    /**设备的型号*/
    public static String deviceModel = "";
    /**系统版本号 */
    public static String sysVerison;

    /**应用程序名字*/
    public static String appName;
    /**应用用程序版本*/
    public static String appVersionName;
    /**应用用程序版本号*/
    public static String appVersionCode;
    /**应用程序包名*/
    public static String appPackageName = "";

    /**设备的高度*/
    public static int height = 0;
    /**设备的宽度*/
    public static int width = 0;
    public static float density;
    public static int densityDip;

    public static void initAppInfo(Context context){
        appPackageName = context.getPackageName();
        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(appPackageName, 0);
            appVersionName = info.versionName;
            appVersionCode = info.versionCode+"";
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, e.toString());
        }

        DisplayMetrics dm = new DisplayMetrics();
        WindowManager manager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;
        density = dm.density;
        densityDip = dm.densityDpi;

        try{
            TelephonyManager tm = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
            deviceId = tm.getDeviceId();
        }catch(Exception e){
            deviceId = "";
            Log.e(TAG, e.toString());
        }
        if(deviceId == null){
            deviceId = "";
        }
        if(deviceId == null || deviceId.length() < 10){
            deviceId = phoneId();
        }
        deviceModel = android.os.Build.MODEL;
        sysVerison = android.os.Build.VERSION.RELEASE;
    }
    @SuppressWarnings("deprecation")
    public static String phoneId(){
        String m_szDevIDShort = "35" + //we make this look like a valid IMEI
                Build.BOARD.length()%10 +
                Build.BRAND.length()%10 +
                Build.CPU_ABI.length()%10 +
                Build.DEVICE.length()%10 +
                Build.DISPLAY.length()%10 +
                Build.HOST.length()%10 +
                Build.ID.length()%10 +
                Build.MANUFACTURER.length()%10 +
                Build.MODEL.length()%10 +
                Build.PRODUCT.length()%10 +
                Build.TAGS.length()%10 +
                Build.TYPE.length()%10 +
                Build.USER.length()%10 ;
        return m_szDevIDShort;
    }
    /**
     * 设置设备类型
     * @param platForm
     */
    public void setAppPlatform(String platForm){
        appPlatForm = platForm;
    }

    /**
     * 设置浏览器标志
     * @param ua
     */
    public void setAppUA(String ua){
        appUA = ua;
    }
}
