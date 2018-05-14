package com.lingkj.android.edumap.ui.index.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lingkj.android.edumap.R;
import com.lingkj.android.edumap.base.BaseActivity;
import com.lingkj.android.edumap.bean.TabEntity;
import com.lingkj.android.edumap.customview.NoScrollViewPager;
import com.lingkj.android.edumap.ui.index.fragment.ChooseClassFragment;
import com.lingkj.android.edumap.ui.index.fragment.IndexFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * author: panlijun
 * time: 2018/5/10 上午11:23
 * detail:首页
 */
public class IndexActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;
    @BindView(R.id.vp_viewpager)
    NoScrollViewPager mViewPager;
    private ArrayList<Fragment> mModulles;


    private String[] mTitles;
    private int[] mIconUnselectIds = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private int[] mIconSelectIds = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.model_activity_index;
    }

    @Override
    protected void initView() {
        mTitles = getResources().getStringArray(R.array.app_bottom_tab_name);

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setTabData(mTabEntities);

        mModulles = new ArrayList<>();
        mModulles.add(new IndexFragment());
        mModulles.add(new IndexFragment());
        mModulles.add(new ChooseClassFragment());
        mModulles.add(new IndexFragment());


        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return null == mModulles ? null : mModulles.get(position);
            }

            @Override
            public int getCount() {
                return null == mModulles ? 0 : mModulles.size();
            }
        });
        mViewPager.setOffscreenPageLimit(3);
//
        mViewPager.setCurrentItem(0);


        //点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

    }


    @Override
    protected boolean showBack() {
        return false;
    }

    @Override
    protected void errorReLoad() {

    }
}
