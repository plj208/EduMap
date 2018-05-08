package com.lingkj.android.edumap.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * @author panlijun
 *
 * 懒加载
 */
public abstract  class LazyFragment extends BaseFragment {

    protected   boolean mVisiable=false;
    protected boolean mCreated=false;
    private  boolean isFirst=false;
    protected  abstract  void  loadData();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(mCreated&&mVisiable&&isFirst){
            loadData();
            isFirst=false;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mVisiable=isVisibleToUser;
        if(mVisiable&&mCreated&&isFirst){
            loadData();
            isFirst=false;
        }
    }

    @Override
    public void onDestroyView() {
        isFirst=true;
        mCreated=false;
        mVisiable=false;
        super.onDestroyView();
    }

}
