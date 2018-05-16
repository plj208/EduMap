package com.lingkj.android.edumap.ui.mine.fragment;

        import android.view.View;

        import com.jaeger.library.StatusBarUtil;
        import com.lingkj.android.edumap.R;
        import com.lingkj.android.edumap.base.BaseFragment;
        import com.lingkj.android.edumap.ui.index.activity.IndexActivity;

public class MineFragment extends BaseFragment {
    @Override
    protected int getContentView() {
        return R.layout.model_fragment_mine;
    }

    @Override
    protected void initView() {
        ((IndexActivity) getContext()).getToolbar().setVisibility(View.GONE);
    }

    @Override
    protected void onReLoad() {

    }
}
