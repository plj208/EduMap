package com.lingkj.android.library.recycle;

import android.view.View;

/**
 * @author panlijun
 */

public interface IViewHolderFactory<T> {
        T generatorViewHolder(View view, BaseViewHolder.OnItemClickListener onItemClickListener);
}
