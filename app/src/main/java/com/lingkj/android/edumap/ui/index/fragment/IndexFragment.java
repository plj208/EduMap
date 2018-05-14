package com.lingkj.android.edumap.ui.index.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.stetho.common.LogUtil;
import com.lingkj.android.edumap.R;
import com.lingkj.android.edumap.base.BaseFragment;
import com.lingkj.android.edumap.base.LazyFragment;
import com.lingkj.android.edumap.utils.GlideImageLoader;
import com.lingkj.android.edumap.utils.ToastUtils;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: panlijun
 * time: 2018/5/10 上午11:27
 * detail:
 */
public class IndexFragment extends BaseFragment {

    @BindView(R.id.sp_refresh)
    SmartRefreshLayout mRefresh;
    @BindView(R.id.bn_img)
    Banner mBanner;

    private List<Integer> mImgs=new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.model_fragment_index;
    }


    @Override
    protected void initView() {
        mImgs.add(R.mipmap.ic_launcher);
        mImgs.add(R.mipmap.ic_launcher);
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(mImgs);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    @Override
    protected void onReLoad() {

    }

    @Override
    public void onStart() {
        super.onStart();
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }
}
