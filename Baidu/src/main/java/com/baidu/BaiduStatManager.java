package com.baidu;

import android.app.Activity;
import android.content.Context;

import com.baidu.mobstat.StatService;

/**
 * Created by Jbtm on 2017/8/1.
 * 百度统计服务
 */
public class BaiduStatManager {

    /**
     * 启动百度统计服务
     *
     * @param activity 上下文对象
     */
    public static void startStatService(Activity activity, String appKey) {
        StatService.setAppKey(appKey);
        StatService.start(activity);
    }

    /**
     * 唤醒事件
     * @param activity 上下文对象
     */
    public static void onResume(Activity activity){
        StatService.onResume(activity);
    }

    /**
     * 暂停事件
     * @param activity 上下文对象
     */
    public static void onPause(Activity activity){
        StatService.onPause(activity);
    }

    /**
     * 页面启动事件
     * @param context 上下文想
     * @param tag 标识
     */
    public static void onPageStart(Context context, String tag){
        StatService.onPageStart(context, tag);
    }

    /**
     * 页面结束事件
     * @param context 上下文对象
     * @param tag 标识
     */
    public static void onPageEnd(Context context, String tag){
        StatService.onPageEnd(context, tag);
    }

}
