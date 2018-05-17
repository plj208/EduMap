package com.lingkj.android.edumap.ui.active.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;

import com.lingkj.android.edumap.R;
import com.lingkj.android.edumap.adapter.ActiveAdapter;
import com.lingkj.android.edumap.base.BaseFragment;
import com.lingkj.android.edumap.customview.NoScrollViewPager;
import com.lingkj.android.edumap.ui.index.activity.IndexActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 时间：2018/5/14
 * 作者：李飞磊
 * 描述：活动模块
 */
public class ActiveFragment extends BaseFragment {

    @BindView(R.id.tl_active_fragment)
    TabLayout mTabLayout;
    @BindView(R.id.vp_active_fragment)
    NoScrollViewPager mViewPager;

    private String[] mTitles;
    private List<Fragment> fragments = new ArrayList<>();
    private ActiveAdapter mAdapter;

    @Override
    protected int getContentView() {
        return R.layout.model_fragment_active;
    }

    @Override
    protected void initView() {
        ((IndexActivity) getContext()).getToolbar().setVisibility(View.GONE);
        mTitles = getResources().getStringArray(R.array.text_active_title);
        fragments.add(new ActivityFragment());
        fragments.add(new NewsFragment());
        mAdapter = new ActiveAdapter(getActivity().getSupportFragmentManager(), mTitles, fragments);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onReLoad() {

    }
}
