package com.lingkj.android.edumap.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

/**
 * @author panlijun
 * @detail Dialog工具类
 */
public class DialogUtils implements Dialog.OnKeyListener {

    private Context context;
    private LoadingFragment mProgressDialog;
    private static DialogUtils mDialog;
    private DialogUtils(Context context) {
        this.context = context;
    }

    public  static DialogUtils getInstance(Context context){
        return new DialogUtils(context);
    }


    public  void showProgressDialog(String msg){
        showProgressDialog(msg);

    }
    public  void showProgressDialog(String msg,boolean cancelable){
        if (mProgressDialog == null){
            synchronized (DialogUtils.this){
                if (mProgressDialog==null){
                    AppCompatActivity activity = (AppCompatActivity) context;
                    FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                    Fragment fragment = activity.getSupportFragmentManager().findFragmentByTag(LoadingFragment.class.getName());
                    if (fragment != null) {
                        ft.remove(fragment);
                    }
                    ft.addToBackStack(null);
                    mProgressDialog = new LoadingFragment();
                    mProgressDialog.setMessage(msg);
                    mProgressDialog.setCancelable(cancelable);
                    mProgressDialog.show(ft, LoadingFragment.class.getName());
                }
            }
        }
    }
    public  void closeProgressDialog(){
        if(mProgressDialog!=null) {
            synchronized (DialogUtils.this) {
                if (mProgressDialog != null) {
                    try {
                        mProgressDialog.dismiss();
                    }catch (Exception e){

                    }finally {
                        mProgressDialog = null;
                    }
                }
            }
        }
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (event.getKeyCode()==KeyEvent.KEYCODE_BACK) {
            closeProgressDialog();
            return true;

        }
        return false;
    }
}
