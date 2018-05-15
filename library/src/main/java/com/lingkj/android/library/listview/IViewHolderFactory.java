package com.lingkj.android.library.listview;

import android.view.View;


/**
 * author: panlijun
 * time: 2018/5/14 下午3:50
 * detail:
 */
public interface IViewHolderFactory<T> {

    T generatorViewHolder(View view,int  pos);

}
