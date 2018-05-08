package com.lingkj.android.library.recycle;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * @author panlijun
 */


public class LiveRecyclerAdapter<D extends LiveData,V extends BaseViewHolder<D>> extends BaseRecycleAdapter <D,V>{

    public LiveRecyclerAdapter(List<D> datas, int layoutId) {
        super(datas, layoutId);
    }

    public LiveRecyclerAdapter(int layoutId) {
        super(layoutId);
    }
    @Override
    public void setData(@NonNull final List<D> newDatas){
        if (mDatas == null) {
            mDatas = newDatas;
            notifyItemRangeInserted(0, newDatas.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mDatas.size();
                }

                @Override
                public int getNewListSize() {
                    return newDatas.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    D old=mDatas.get(oldItemPosition);
                    D newData=newDatas.get(newItemPosition);
                    return old.areItemTheSame(newData);
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    D oldProduct = mDatas.get(oldItemPosition);
                    D newProduct = newDatas.get(newItemPosition);
                    return oldProduct.areContentTheSame(newProduct);
                }
            });
            mDatas = newDatas;
            result.dispatchUpdatesTo(this);
        }
    }
}
