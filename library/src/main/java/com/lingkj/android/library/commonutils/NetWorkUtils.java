package com.lingkj.android.library.commonutils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author: panlijun
 * time: 2018/5/8 上午11:37
 * detail:网络管理工具类
 */
public class NetWorkUtils {

    /**
     * 检查网络是否可用
     * @param paramContext
     * @return
     */
     public static  boolean isNetConnected(Context paramContext){
         NetworkInfo localNetworkInfo=((ConnectivityManager)paramContext.
                 getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
         if ((localNetworkInfo!=null)&&(localNetworkInfo.isAvailable()))
             return true;
         return false;

     }

    /**
     * 检查WIFI是否可用
     * @param paramContext
     * @return
     */
     public  static  boolean isWifiConnected(Context paramContext){
        ConnectivityManager cm= (ConnectivityManager) paramContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm!=null){
            NetworkInfo networkInfo=cm.getActiveNetworkInfo();
            if (networkInfo!=null&&networkInfo.getType()==ConnectivityManager.TYPE_WIFI){
                return true;
            }
        }
        return false;
     }

    /**
     * 检查链接是否可用
     * @param link
     * @return
     */
     public  static  boolean isLinkAvailable(String link){
         Pattern pattern = Pattern.compile("^(http://|https://)?((?:[A-Za-z0-9]+-[A-Za-z0-9]+|[A-Za-z0-9]+)\\.)+([A-Za-z]+)[/\\?\\:]?.*$", Pattern.CASE_INSENSITIVE);
         Matcher matcher = pattern.matcher(link);
         if (matcher.matches()) {
             return true;
         }
         return false;
     }

}
