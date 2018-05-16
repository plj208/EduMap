package com.lingkj.android.library.recycle;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author panlijun
 */

public abstract class BaseViewHolder<D> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView, final OnItemClickListener onItemClickedListener) {
        super(itemView);
        if (onItemClickedListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position >= 0) {
                        onItemClickedListener.onItemClicked(v, getAdapterPosition());
                    }
                }
            });
        }
    }


    public abstract void setData(D d);

    public interface OnItemClickListener {
        void onItemClicked(View v, int position);
    }
}
