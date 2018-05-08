package com.lingkj.android.library.recycle;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author panlijun
 */


public class HeaderAdapter  extends RecyclerView.Adapter{
    private RecyclerView.Adapter mAdapter;
    private List<View> mHeaders;

    public HeaderAdapter(RecyclerView.Adapter adapter,List<View> headers){
        this.mAdapter=adapter;
        this.mHeaders=headers;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType<mHeaders.size()){
            return new CommonViewHolder(mHeaders.get(viewType));
        }
        else {
            return mAdapter.onCreateViewHolder(parent, viewType-mHeaders.size());
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position>=mHeaders.size()) {
            mAdapter.onBindViewHolder(holder, position-mHeaders.size());
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        if(position>=mHeaders.size()) {
            mAdapter.onBindViewHolder(holder, position-mHeaders.size(), payloads);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position>=mHeaders.size()){
            return mHeaders.size()+mAdapter.getItemViewType(position);
        }else{
            return position;
        }

    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        mAdapter.setHasStableIds(hasStableIds);
    }

    @Override
    public long getItemId(int position) {
        return mAdapter.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return mAdapter.getItemCount()+mHeaders.size();
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        if(holder.getAdapterPosition()>=mHeaders.size()) {
            mAdapter.onViewRecycled(holder);
        }
    }

    @Override
    public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        if(holder.getAdapterPosition()>=mHeaders.size()) {
            return mAdapter.onFailedToRecycleView(holder);
        } else {
            return false;
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        if(holder.getAdapterPosition()>=mHeaders.size()) {
            mAdapter.onViewAttachedToWindow(holder);
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        if(holder.getAdapterPosition()>=mHeaders.size()) {
            mAdapter.onViewDetachedFromWindow(holder);
        }
    }

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        mAdapter.registerAdapterDataObserver(observer);
    }

    @Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        mAdapter.unregisterAdapterDataObserver(observer);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        mAdapter.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        mAdapter.onDetachedFromRecyclerView(recyclerView);
    }
}
