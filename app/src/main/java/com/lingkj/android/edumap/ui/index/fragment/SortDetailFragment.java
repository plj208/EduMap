package com.lingkj.android.edumap.ui.index.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lingkj.android.edumap.R;
import com.lingkj.android.edumap.base.BaseFragment;
import com.lingkj.android.edumap.utils.SystemUtils;
import com.lingkj.android.library.recycle.RecyclerDecorate;

import butterknife.BindView;

/**
 * author: panlijun
 * time: 2018/5/15 上午10:33
 * detail:
 */
public class SortDetailFragment extends BaseFragment {


    @BindView(R.id.xv_right)
    RecyclerView mRecyclerView;

    @Override
    protected int getContentView() {
        return R.layout.model_fragment_sortdetail;
    }

    @Override
    protected void initView() {
        GridLayoutManager mManager=new GridLayoutManager(getActivity(),3);
//        mManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return 0;
//            }
//        });
        mRecyclerView.setLayoutManager(mManager);
        RecyclerDecorate mItemDecode=new RecyclerDecorate();
        mItemDecode.setDividerSize(0, SystemUtils.dpToPx(getActivity(),1f));
        mItemDecode.setColor(getResources().getColor(R.color.line_bg));
        mRecyclerView.addItemDecoration(mItemDecode);


    }

    @Override
    protected void onReLoad() {

    }
}
