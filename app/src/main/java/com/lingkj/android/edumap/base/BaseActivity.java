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
import com.lingkj.android.edumap.utils.ToastUtils;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author panlijun
 * @detail activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {


    private Context mContext;
    protected DialogUtils mDialog;

    ViewGroup mContainerView;

    private ViewGroup mEmptyView;

    private ViewGroup mErrorView;

    private TextView mEmptyTips;

    private ImageView mErrorImage;

    private Unbinder binder;

    @BindView(R.id.baseToolbar)
    Toolbar toolbar;
    @BindView(R.id.activityTitle)
    TextView tvTitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        initRealContent();
        binder = ButterKnife.bind(this);
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

//    /**
//     * 设置Toolbar
//     *
//     * @param title            标题
//     * @param navClickListener 返回事件
//     */
//    protected void setToolbar(Context context, String title, View.OnClickListener navClickListener) {
//        try {
//            Class clazz = Class.forName(context.getPackageName() + ".R$id");
//            if (clazz != null) {
//                Field toolbarField = clazz.getField("toolbar");
//                if (toolbarField != null) {
//                    Toolbar toolbar = findViewById(toolbarField.getInt(null));
//                    toolbar.setTitle(title);
//                    setSupportActionBar(toolbar);
//                    toolbar.setNavigationOnClickListener(navClickListener);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


//    protected abstract boolean showBack();


    public void setUpToolbar(int resId, String title) {
        if (toolbar != null) {
            toolbar.setNavigationIcon(resId);
            toolbar.setTitle("");
            tvTitle.setText(title);
            setSupportActionBar(toolbar);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }


    protected abstract void errorReLoad();
    protected  boolean showBack(){

        return false;
    };

    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binder.unbind();
    }
}
