package com.lingkj.android.library.listview;


import android.view.View;

/**
 * author: panlijun
 * time: 2018/5/14 下午3:45
 * detail:
 */
public abstract  class BaseViewHolder<D> {
    public BaseViewHolder(View view){
        super();
    };

    public abstract void setData(D d);


}

