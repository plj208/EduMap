package com.lingkj.android.library.recycle;


import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * @author panlijun
 */

public class BaseRecycleAdapter<D, V extends BaseViewHolder<D>> extends RecyclerView.Adapter<V> {
    protected List<D> mDatas;
    private IViewHolderFactory<V> mFactory;
    private int mLayoutId;
    private int mLayoutId1;
    private int mType = 0;
    protected BaseViewHolder.OnItemClickListener mOnItemClickedListener;

    protected BaseRecycleAdapter() {
    }

    public BaseRecycleAdapter(List<D> datas, int layoutId) {
        this.mDatas = datas;
        this.mLayoutId = layoutId;
    }


    public BaseRecycleAdapter(int layoutId) {
        this.mLayoutId = layoutId;
    }

    @Override
    public V onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);

        return mFactory.generatorViewHolder(view, mOnItemClickedListener);
    }

    @Override
    public void onBindViewHolder(V holder, int position) {
        holder.setData(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public BaseRecycleAdapter setOnItemClickListener(BaseViewHolder.OnItemClickListener onItemClickListener) {
        this.mOnItemClickedListener = onItemClickListener;
        return this;
    }

    public BaseRecycleAdapter setFactory(IViewHolderFactory<V> factory) {
        this.mFactory = factory;
        return this;
    }

    public void updateData(final List<D> newDatas) {
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
                return mDatas.get(oldItemPosition).equals(newDatas.get(newItemPosition));
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return mDatas.get(oldItemPosition).equals(newDatas.get(newItemPosition));
            }
        }, true);
        mDatas = newDatas;
        result.dispatchUpdatesTo(this);
    }

    public void setData(final List<D> newDatas) throws Exception {
        if (mDatas == null) {
            this.mDatas = newDatas;
            notifyDataSetChanged();
        } else {
            throw new Exception("data can't be set twice");
        }
    }

    public int getType() {
        return mType;
    }

}
