package com.lingkj.android.edumap.ui.index.constract;

import io.reactivex.disposables.CompositeDisposable;

/**
 * author: panlijun
 * time: 2018/5/14 下午1:26
 * detail:
 */
public class ChooseCityPresent implements ChooseCityConstract.Present {
    private CompositeDisposable mDisposables;
    private ChooseCityConstract.View mView;

    public ChooseCityPresent(ChooseCityConstract.View view){
        this.mView=view;
        mDisposables=new CompositeDisposable();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        mDisposables.clear();

    }
}
