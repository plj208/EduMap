package com.lingkj.android.edumap.ui.active.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.lingkj.android.edumap.R;
import com.lingkj.android.edumap.adapter.ActivityAdapter;
import com.lingkj.android.edumap.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 时间：2018/5/14
 * 作者：李飞磊
 * 描述：活动模块
 */
public class ActivityFragment extends BaseFragment {

    @BindView(R.id.iv_top_image_activity_fragment)
    ImageView mTopImageView;
    @BindView(R.id.stl_activity_fragment)
    SlidingTabLayout mTabLyout;
    @BindView(R.id.vp_activity_fragment)
    ViewPager mViewPager;

    private String[] mTitles;
    private List<Fragment> fragments = new ArrayList<>();
    private ActivityAdapter mAdapter;

    @Override
    protected int getContentView() {
        return R.layout.model_fragment_activity;
    }

    @Override
    protected void initView() {
        mTitles = getResources().getStringArray(R.array.activity_area);
        for (int i = 0; i < mTitles.length; i++) {
            fragments.add(ActivityListFragment.newInstance());
        }
        mAdapter = new ActivityAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapter);
        mTabLyout.setViewPager(mViewPager,mTitles);
    }

    @Override
    protected void onReLoad() {

    }
}
