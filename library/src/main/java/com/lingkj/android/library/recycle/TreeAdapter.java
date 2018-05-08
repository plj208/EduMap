package com.lingkj.android.library.recycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author panlijun
 */


public class TreeAdapter<D extends TreeItem,VH extends BaseViewHolder<D>> extends BaseRecycleAdapter<D,VH> {
    private MutiTypeHolderFactory<VH> mFactory;
    int[] mLayouts;
    List<D> mData;
    public TreeAdapter(List<D> data){
        this.mData=data;
    }
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(mLayouts[viewType],parent,false);
        return mFactory.generatorViewHolder(view,mOnItemClickedListener,viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.setData(mData.get(position));
    }


    public void setFactory(MutiTypeHolderFactory<VH> factory) {
        mFactory=factory;
    }

    public void setLayout(int ...layouts){
        mLayouts=layouts;
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getLevel();
    }

    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }

    public void addData(List<D> data,int position){
        mData.addAll(position,data);
        notifyItemRangeInserted(position,data.size());
    }

    public void removeData(int from,int length){
        for (int i=0;i<length;i++){
            mData.remove(from);
        }
        notifyItemRangeRemoved(from,length);
    }
}
