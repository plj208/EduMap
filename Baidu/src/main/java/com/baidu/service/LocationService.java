package com.baidu.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.baidu.BaiduGeoManager;
import com.baidu.callback.OnBDLocationCallback;
import com.baidu.location.BDLocation;

/**
 * Created by mrper on 17-4-20.
 * 定位服务的Service
 */
public class LocationService extends Service {

    /**
     * 定位数据标识
     */
    public static final String LocationDataTag = "LocationDataTag";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        BaiduGeoManager.getCurrentLocation(this, 3000, new OnBDLocationCallback() {
            @Override
            public void onReceiveLocation(BDLocation location) {
                super.onReceiveLocation(location);
                Intent locationIntent = new Intent();
                locationIntent.putExtra(LocationDataTag, location);
                sendBroadcast(locationIntent);
            }

            @Override
            protected void showFailedMessage(String message) {
                Toast.makeText(LocationService.this, message, Toast.LENGTH_SHORT).show();
            }
        });
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
