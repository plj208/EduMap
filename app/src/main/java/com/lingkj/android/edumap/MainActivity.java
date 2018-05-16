package com.lingkj.android.edumap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lingkj.android.edumap.base.BaseActivity;
import com.lingkj.android.edumap.net.ExceptionHandle;
import com.lingkj.android.edumap.net.RxNet;
import com.lingkj.android.edumap.net.RxSchedulers;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {


    CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        Observable<Object> net = RxNet.getInstance().getIRxNet().getNet();
        RxSchedulers.applySchedulers().apply(net).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                //统一管理请求
                mCompositeDisposable.add(d);
            }

            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable e) {
//                ExceptionHandle.ApiException apiException = ExceptionHandle.handleException(e);

            }

            @Override
            public void onComplete() {

            }
        });

    }



    @Override
    protected void errorReLoad() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }

}
