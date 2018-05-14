package com.lingkj.android.edumap.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.provider.Settings;

import com.lingkj.android.library.commonutils.NetWorkUtils;

import java.io.File;

public class SystemUtils  {


    /**
     * 获取文件的大小
     * @param file
     * @return
     */
    public static long getFolderSize(File file) {
        long result=0;
        if (file.isDirectory()) {
            File[] childer=file.listFiles();
            for (File singleFile:childer) {
                result+=getFolderSize(singleFile);
            }
        }else{
            result+=file.length();
        }
        return result;
    }

    /**
     * 打开网络设置页面
     *
     * @param context 网络设置页面
     */
    public static void openNetworkSettingsPage(Context context) {
        int type = NetWorkUtils.getActiveNetworkType(context);
        String action = Settings.ACTION_WIRELESS_SETTINGS;
        if (type == ConnectivityManager.TYPE_MOBILE)
            action = Settings.ACTION_DATA_ROAMING_SETTINGS;
        else if (type == ConnectivityManager.TYPE_WIFI)
            action = Settings.ACTION_WIFI_SETTINGS;
        Intent intent = new Intent();
        intent.setAction(action);
        context.startActivity(intent);
    }


    /**
     * 获取颜色资源
     *
     * @param context 上下文对象
     * @param colorId 颜色ResId
     * @return
     */
    @SuppressWarnings("deprecation")
    public static int getColor(Context context, int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(colorId);
        }
        return context.getResources().getColor(colorId);
    }

}
