package com.lingkj.android.edumap.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingkj.android.edumap.R;
import com.lingkj.android.edumap.utils.DialogUtils;

/**
 * @author panlijun
 * @detail activity基类
 */
public abstract  class BaseActivity extends AppCompatActivity {


    private Context mContext;
    protected DialogUtils mDialog;

    ViewGroup mContainerView;

    private ViewGroup mEmptyView;

    private ViewGroup mErrorView;

    private TextView mEmptyTips;

    private ImageView mErrorImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        initRealContent();
        this.initView();
    }

    void setContentView() {
        setContentView(R.layout.model_activity_base);
        mContainerView = findViewById(R.id.fl_container);
        // mLoadSir=LoadSir.getDefault();

    }

    private void initRealContent() {
        getLayoutInflater().inflate(getLayoutId(), mContainerView);
    }

    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract boolean showBack();

    //初始化view

    protected void setUpToolbar(Toolbar toolbar) {
        if (toolbar != null) {
            if (showBack()) {
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//                toolbar.setNavigationIcon(R.drawable.ic_back);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
            }else{
                toolbar.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }



    protected void showError() {
        if (mErrorView == null) {
            mErrorView = (ViewGroup) getLayoutInflater().inflate(R.layout.commen_net_error, new FrameLayout(this), false);
            mContainerView.addView(mErrorView);
            mErrorImage = mErrorView.findViewById(R.id.iv_error);
            mErrorView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    errorReLoad();
                    mErrorView.setVisibility(View.GONE);
                    // showSuccess();
                }
            });
        }

        mErrorView.setVisibility(View.VISIBLE);
    }

    protected void showEmpty(String tips) {
        if (mEmptyView == null) {
            mEmptyView = (ViewGroup) getLayoutInflater().inflate(R.layout.commen_net_empty, new FrameLayout(this), false);
            mEmptyTips = mEmptyView.findViewById(R.id.tvEmptyTips);
            mEmptyView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            mContainerView.addView(mEmptyView);
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

    protected abstract void errorReLoad();

}
