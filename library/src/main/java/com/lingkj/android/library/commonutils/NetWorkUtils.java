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
     * 获取网络管理服务类对象
     * @param context 上下文对象
     * @return 网络管理服务类对象
     */
    private static ConnectivityManager getConnectivityManager(Context context){
        return (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }


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



    /**
     * 获取当前处于激活状态的网络类型，返回-1表示无任何连接
     * info in this {@code NetworkInfo} pertains.
     * @return one of {@link ConnectivityManager#TYPE_MOBILE}, {@link
     * ConnectivityManager#TYPE_WIFI}, {@link ConnectivityManager#TYPE_WIMAX}, {@link
     * ConnectivityManager#TYPE_ETHERNET},  {@link ConnectivityManager#TYPE_BLUETOOTH}, or other
     * types defined by {@link ConnectivityManager}
     */
    public static int getActiveNetworkType(Context context){
        NetworkInfo networkInfo = getConnectivityManager(context).getActiveNetworkInfo();
        if(networkInfo != null)
            return networkInfo.getType();
        return -1;
    }

}
