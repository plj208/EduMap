package com.lingkj.android.edumap.base.basemvp;

/**
 * author: panlijun
 * time: 2018/5/8 上午11:16
 * detail:
 */
public interface BaseView {
    /*******内嵌加载*******/
    void showLoading(String title);
    void stopLoading();
    void showErrorTip(String msg);
}
