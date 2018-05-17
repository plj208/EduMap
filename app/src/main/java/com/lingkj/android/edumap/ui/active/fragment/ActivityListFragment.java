package com.lingkj.android.edumap.ui.active.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lingkj.android.edumap.R;
import com.lingkj.android.edumap.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;

public class ActivityListFragment extends BaseFragment {
    @BindView(R.id.rv_activity_list_fragment)
    RecyclerView mRecyclerView;
    @BindView(R.id.srl_activity_list_fragment)
    SmartRefreshLayout mSmartRefreshLayout;

    public static ActivityListFragment newInstance() {
        ActivityListFragment fragment = new ActivityListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentView() {
        return R.layout.model_fragment_activity_list;
    }

    @Override
    protected void initView() {
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void onReLoad() {

    }
}
