package com.lingkj.android.edumap.ui.index.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lingkj.android.edumap.R;
import com.lingkj.android.edumap.bean.IndexClass;

import java.util.List;

import butterknife.ButterKnife;

/**
 * author: panlijun
 * time: 2018/5/16 下午3:35
 * detail:
 */
public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassHolder> {

    private List<IndexClass> mList;
    private Context mContext;

    public ClassAdapter(Context context, List<IndexClass> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public ClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_fragment_class_content, parent, false);
        return new ClassHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassHolder holder, int position) {

        switch (position) {
            case 0:
                break;
            default:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ClassHolder extends RecyclerView.ViewHolder {


        public ClassHolder(View itemView) {
            super(itemView);

        }
    }
}
