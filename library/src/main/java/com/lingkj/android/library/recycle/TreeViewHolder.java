package com.lingkj.android.library.recycle;

import android.view.View;

/**
 * @author panlijun
 */

public abstract class TreeViewHolder<T extends TreeItem> extends BaseViewHolder<T>{


    public TreeViewHolder(View itemView, OnItemClickListener onItemClickedListener) {
        super(itemView, onItemClickedListener);
    }

    public abstract void setData(T t);
}
