package com.lingkj.android.library.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lingkj.android.adapterlibrary.R;
import com.lingkj.android.library.recycle.BaseRecycleAdapter;

import java.util.List;

/**
 * author: panlijun
 * time: 2018/5/14 下午3:37
 * detail:
 */
public class BaseListViewAdapter<D extends BaseViewHolder<D>> extends BaseAdapter {

    private List<D> mList;
    private Context mContext;
    private int mLayoutId;
    private IViewHolderFactory mFactory;
    public  BaseListViewAdapter(Context context,List<D> list,int layoutId){
        this.mList=list;
        this.mContext=context;
        this.mLayoutId=layoutId;

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null)
        {
            convertView=LayoutInflater.from(mContext).inflate(mLayoutId,parent,false);
            convertView.setTag(mFactory);
        }else{
            mFactory= (IViewHolderFactory) convertView.getTag();

        }
        mFactory.generatorViewHolder(convertView,position);
        return convertView;
    }


    public BaseListViewAdapter<D> setFactory(IViewHolderFactory factory) {
        this.mFactory = factory;
        return this;
    }
    public void setData(final List<D> newDatas) throws Exception {
        if (mList == null) {
            this.mList = newDatas;
            notifyDataSetChanged();
        } else {
            throw new Exception("data can't be set twice");
        }
    }
}
