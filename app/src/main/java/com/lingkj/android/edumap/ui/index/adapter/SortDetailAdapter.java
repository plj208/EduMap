package com.lingkj.android.edumap.ui.index.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lingkj.android.edumap.R;
import com.lingkj.android.library.recycle.BaseRecycleAdapter;
import com.lingkj.android.library.recycle.BaseViewHolder;

import java.util.List;

/**
 * author: panlijun
 * time: 2018/5/15 上午11:08
 * detail:
 */
public class SortDetailAdapter<D, V extends BaseViewHolder<D>> extends BaseRecycleAdapter {
    protected List<D> mDatas;
    private int mLayoutId;
    private Context mContext;

    public SortDetailAdapter(Context context,List<D> datas, int layoutId) {
        this.mContext=context;
        this.mDatas = datas;
        this.mLayoutId = layoutId;
    }


    private int checkedPosition;

    public void setCheckedPosition(int checkedPosition) {
        this.checkedPosition = checkedPosition;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.setData(mDatas);
//        if (position == checkedPosition) {
//                mView.setBackgroundColor(Color.parseColor("#f3f3f3"));
//                tvName.setTextColor(Color.parseColor("#0068cf"));
//        } else {
//                mView.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                tvName.setTextColor(Color.parseColor("#1e1d1d"));
//        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=null;
        switch (viewType)
        {
            case 0:
                view= LayoutInflater.from(mContext).inflate(R.layout.item_fragment_sortdetail_title,parent,false);
                break;
            case 1:
                view=LayoutInflater.from(mContext).inflate(R.layout.item_fragent_sortdetail_content,parent,false);
                break;
        }

        return new SortDetailHolder(view,null);
    }


    private class SortDetailHolder extends BaseViewHolder<String> {

        private TextView tvName;
        private View mView;

        public SortDetailHolder(View itemView, OnItemClickListener onItemClickedListener) {
            super(itemView, onItemClickedListener);
        }


//        SortDetailHolder(View itemView, int type, RvListener listener) {
//            super(itemView, type, listener);
//            this.mView = itemView;
//            tvName = (TextView) itemView.findViewById(R.id.tv_sort);
//        }

//        @Override
//        public void bindHolder(String string, int position) {
//            tvName.setText(string);
//            if (position == checkedPosition) {
//                mView.setBackgroundColor(Color.parseColor("#f3f3f3"));
//                tvName.setTextColor(Color.parseColor("#0068cf"));
//            } else {
//                mView.setBackgroundColor(Color.parseColor("#FFFFFF"));
//                tvName.setTextColor(Color.parseColor("#1e1d1d"));
//            }
//        }

        @Override
        public void setData(String s) {

        }
    }
}
