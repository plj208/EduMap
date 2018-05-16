package com.lingkj.android.edumap.ui.index.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lingkj.android.edumap.R;
import com.lingkj.android.edumap.base.BaseFragment;
import com.lingkj.android.edumap.utils.SystemUtils;
import com.lingkj.android.edumap.utils.ToastUtils;
import com.lingkj.android.library.recycle.BaseRecycleAdapter;
import com.lingkj.android.library.recycle.BaseViewHolder;
import com.lingkj.android.library.recycle.IViewHolderFactory;
import com.lingkj.android.library.recycle.RecyclerDecorate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: panlijun
 * time: 2018/5/15 上午10:33
 * detail:
 */
public class SortDetailFragment extends BaseFragment {


    @BindView(R.id.xv_right)
    RecyclerView mRecyclerView;

    private BaseRecycleAdapter mAdapter;

    private List<String> mList=new ArrayList<>();

    public SortDetailFragment() {

    }

    @Override
    protected int getContentView() {
        return R.layout.model_fragment_sortdetail;
    }

    @Override
    protected void initView() {

        GridLayoutManager mManager = new GridLayoutManager(getActivity(), 3);

//        mManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return 0;
//            }
//        });
        mRecyclerView.setLayoutManager(mManager);
        RecyclerDecorate mItemDecode = new RecyclerDecorate();
        mItemDecode.setDividerSize(0, SystemUtils.dpToPx(getActivity(), 1f));
        mItemDecode.setColor(getResources().getColor(R.color.line_bg));
        mRecyclerView.addItemDecoration(mItemDecode);
//
        for (int i=0;i<10;i++)
        {
            mList.add("Test"+String.valueOf(i));
        }
//
        mAdapter = new BaseRecycleAdapter(mList, R.layout.item_fragment_sortdetail_title);
        mAdapter.setFactory(new IViewHolderFactory() {
            @Override
            public Object generatorViewHolder(View view, BaseViewHolder.OnItemClickListener onItemClickListener) {
                return new TestHolder(view, onItemClickListener);
            }
        });

        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    protected void onReLoad() {

    }


    class TestHolder extends BaseViewHolder<String> {

        @BindView(R.id.tv_name)
        TextView tvName;
        public TestHolder(View itemView, OnItemClickListener onItemClickedListener) {
            super(itemView, onItemClickedListener);
            ButterKnife.bind(this,itemView);

        }

        @Override
        public void setData(String s) {
            ToastUtils.showShortToast(getActivity(),s);
            tvName.setText(s);
        }
    }
}
