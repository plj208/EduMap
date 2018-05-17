package com.lingkj.android.edumap.ui.chooosecourse.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.flyco.roundview.RoundTextView;
import com.lingkj.android.edumap.R;
import com.lingkj.android.edumap.base.BaseFragment;
import com.lingkj.android.edumap.ui.index.activity.IndexActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 时间：2018/5/14
 * 作者：李飞磊
 * 描述：选课帮Fragment
 */
public class ChooseClassFragment extends BaseFragment {
    @BindView(R.id.rv_select_course_counselor_choose_class_fragment)
    RecyclerView mRecyclerView;
    @BindView(R.id.btn_quick_choose_course_fragment)
    RoundTextView mBtnCommit;
    @BindView(R.id.srf_smart_refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;

    @Override
    protected int getContentView() {
        return R.layout.model_fragment_choose_class;
    }

    @Override
    protected void initView() {
        ((IndexActivity)getContext()).getToolbar().setVisibility(View.VISIBLE);
        mSmartRefreshLayout.setEnableLoadmore(false);
        mSmartRefreshLayout.setEnableAutoLoadmore(false);
        mSmartRefreshLayout.setEnableOverScrollDrag(false);
    }

    @Override
    protected void onReLoad() {

    }

    @OnClick(R.id.btn_quick_choose_course_fragment)
    public void onViewClicked() {
        quickChooseCourse();
    }

    /**
     * 立即选课
     */
    private void quickChooseCourse() {

    }
}
