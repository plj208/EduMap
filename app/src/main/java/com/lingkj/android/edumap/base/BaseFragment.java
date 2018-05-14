package com.lingkj.android.edumap.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingkj.android.edumap.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author panlijun
 * @detail fragment基类
 */
public abstract class BaseFragment extends Fragment {
    private ViewGroup mContainerView;


    private ViewGroup mEmptyView;

    private ViewGroup mErrorView;

    private TextView mEmptyTips;

    private ImageView mErrorImage;

    Unbinder unBinder;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getContentView(), container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unBinder = ButterKnife.bind(this, view);
        initView();
    }

    protected abstract int getContentView();


    protected void showError() {
        if (mErrorView == null) {
            mErrorView = (ViewGroup) getLayoutInflater().inflate(R.layout.commen_net_error, new FrameLayout(getActivity()), false);
            mContainerView.addView(mErrorView);
            mErrorImage = mErrorView.findViewById(R.id.iv_error);
            mErrorView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onReLoad();
                    mErrorView.setVisibility(View.GONE);
                    // showSuccess();
                }
            });
        }

        mErrorView.setVisibility(View.VISIBLE);
    }

    protected void showEmpty(String tips) {
        if (mEmptyView == null) {
            mEmptyView = (ViewGroup) getLayoutInflater().inflate(R.layout.commen_net_empty, new FrameLayout(getActivity()), false);
            mContainerView.addView(mEmptyView);
            mEmptyTips = mEmptyView.findViewById(R.id.tvEmptyTips);
        }
        mEmptyTips.setText(tips);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    protected void showSuccess() {
        if (mEmptyView != null && mEmptyView.getVisibility() == View.VISIBLE) {
            mEmptyView.setVisibility(View.GONE);
        }
        if (mErrorView != null && mErrorView.getVisibility() == View.VISIBLE) {
            mErrorView.setVisibility(View.GONE);
        }
    }
    protected abstract  void initView();

    protected abstract void onReLoad();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unBinder.unbind();
    }
}
