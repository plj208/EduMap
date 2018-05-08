package com.lingkj.android.edumap.utils;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.widget.Toast;

/**
 * author: panlijun
 * time: 2018/5/7 上午9:43
 * detail:弹出框管理器
 */
public class ToastUtils {

    protected void showShortToast(Context context,String msg) {

        if (Build.VERSION.SDK_INT >= 20) {

            Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        } else {

            Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();

        }
    }
}
