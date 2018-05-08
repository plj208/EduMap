package com.lingkj.android.library.recycle;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author panlijun
 */

public  class MutiTypeRecycleAdapter<D extends BaseMutiTypeData,T extends BaseViewHolder<D>> extends BaseRecycleAdapter<D,T> {
    private int[] ids;
    private MutiTypeHolderFactory<T> factory;

    public MutiTypeRecycleAdapter(@LayoutRes int... ids){
        this.ids=ids;
    }

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(ids[viewType],parent,false);
        return factory.generatorViewHolder(view,mOnItemClickedListener,viewType);
    }

    public void setFactory(MutiTypeHolderFactory<T> factory){
        this.factory=factory;
    }

    @Override
    public int getItemViewType(int position) {
        return mDatas.get(position).getType();
    }
}
