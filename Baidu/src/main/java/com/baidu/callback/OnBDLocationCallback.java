package com.baidu.callback;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

/**
 * Created by mrper on 17-4-20.
 * 百度地图定位回调
 */
public abstract class OnBDLocationCallback implements BDLocationListener {

    public static final String TAG = OnBDLocationCallback.class.getSimpleName();

    /** 是否定位有错 **/
    protected boolean isHaveError = true;

    @Override
    public void onReceiveLocation(BDLocation location) {
        isHaveError = true;
        switch(location.getLocType()){
            case BDLocation.TypeServerError:
                showFailedMessage("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                break;
            case BDLocation.TypeNetWorkException:
                showFailedMessage("网络不同导致定位失败，请检查网络是否通畅");
                break;
            case BDLocation.TypeCriteriaException:
                showFailedMessage("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                break;
            default:
                isHaveError = false;
                break;
        }
    }

    /**
     * 显示提示信息
     * @param message
     */
    protected void showFailedMessage(String message){}

}
