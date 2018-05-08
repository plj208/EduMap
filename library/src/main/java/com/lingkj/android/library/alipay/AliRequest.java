package com.lingkj.android.library.alipay;

import android.app.Activity;

import com.alipay.sdk.app.PayTask;

/**
 * author: panlijun
 * time: 2018/5/8 下午2:24
 * detail:发起支付请求
 */
public class AliRequest {
    public static void StartAlipay(final Activity activity, final String payInfo, final PayCallback payCallback){
        // 必须异步调用
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 构造PayTask 对象
                final PayTask alipay = new PayTask(activity);
                // 调用支付接口，获取支付结果
                payCallback.payResult(alipay.pay(payInfo,true));
            }
        }).start();
    }
}
