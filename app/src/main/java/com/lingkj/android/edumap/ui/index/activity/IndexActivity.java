package com.lingkj.android.edumap.ui.index.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;

import com.apkfuns.logutils.LogUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lingkj.android.edumap.R;
import com.lingkj.android.edumap.base.BaseActivity;
import com.lingkj.android.edumap.bean.TabEntity;
import com.lingkj.android.edumap.ui.index.fragment.IndexFragment;
import com.lingkj.android.edumap.utils.AppConstant;
import com.lingkj.android.edumap.utils.ToastUtils;

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

    private String[] mTitles = {"首页", "活动", "周边", "我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private int[] mIconSelectIds = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private IndexFragment mIndexFragment;
    private IndexFragment mTest2;
    private IndexFragment mTest3;
    private IndexFragment mTest4;
    private static int tabLayoutHeight;

    @Override
    protected int getLayoutId() {
        return R.layout.model_activity_index;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment(savedInstanceState);


    }

    @Override
    protected void initView() {
        initTab();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //奔溃前保存位置
        if (tabLayout != null) {
            outState.putInt(AppConstant.HOME_CURRENT_TAB_POSITION, tabLayout.getCurrentTab());
        }
    }


    /**
     * 初始化tab
     */
    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setTabData(mTabEntities);
        //点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                SwitchTo(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }

    /**
     * 切换
     */
    private void SwitchTo(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ToastUtils.showShortToast(IndexActivity.this,String.valueOf(position));
        switch (position) {
            //首页
            case 0:
                operationFragment(transaction,mIndexFragment,mTest2,mTest3,mTest4);
                break;
            case 1:
                operationFragment(transaction,mIndexFragment,mTest2,mTest3,mTest4);

                break;
            case 2:
                operationFragment(transaction,mIndexFragment,mTest2,mTest3,mTest4);

                break;
            case 3:
                operationFragment(transaction,mIndexFragment,mTest2,mTest3,mTest4);

                break;
            default:
                break;
        }
    }

private void operationFragment(FragmentTransaction transaction, Fragment showFragment,Fragment hideFragment1,Fragment hideFragment2,Fragment hideFragment3){
        transaction.hide(hideFragment1);
        transaction.hide(hideFragment2);
        transaction.hide(hideFragment3);
        transaction.show(showFragment);
        transaction.commitAllowingStateLoss();

}
    /**
     * 初始化碎片
     */
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if (savedInstanceState != null) {
            mIndexFragment = (IndexFragment) getSupportFragmentManager().findFragmentByTag("indexFragment");
            mTest2 = (IndexFragment) getSupportFragmentManager().findFragmentByTag("mTest2");
            mTest3 = (IndexFragment) getSupportFragmentManager().findFragmentByTag("mTest3");
            mTest4 = (IndexFragment) getSupportFragmentManager().findFragmentByTag("mTest4");
            currentTabPosition = savedInstanceState.getInt(AppConstant.HOME_CURRENT_TAB_POSITION);
        } else {
            mIndexFragment = new IndexFragment();
            mTest2 = new IndexFragment();
            mTest3 = new IndexFragment();
            mTest4 = new IndexFragment();

            transaction.add(R.id.fl_body, mIndexFragment, "indexFragment");
            transaction.add(R.id.fl_body, mTest2, "mTest2");
            transaction.add(R.id.fl_body, mTest3, "mTest3");
            transaction.add(R.id.fl_body, mTest4, "mTest4");
        }
        transaction.commit();
        SwitchTo(currentTabPosition);
        tabLayout.setCurrentTab(currentTabPosition);
    }
    @Override
    protected boolean showBack() {
        return false;
    }

    @Override
    protected void errorReLoad() {

    }
}
