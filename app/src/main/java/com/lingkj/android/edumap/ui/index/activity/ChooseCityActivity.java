package com.lingkj.android.edumap.ui.index.activity;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingkj.android.edumap.R;
import com.lingkj.android.edumap.base.BaseActivity;
import com.lingkj.android.edumap.customview.NoWrapGridView;
import com.lingkj.android.edumap.ui.index.constract.ChooseCityConstract;
import com.lingkj.android.edumap.ui.index.constract.ChooseCityPresent;
import com.lingkj.android.edumap.utils.ToastUtils;
import com.lingkj.android.library.listview.BaseListViewAdapter;
import com.lingkj.android.library.listview.BaseViewHolder;
import com.lingkj.android.library.listview.IViewHolderFactory;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: panlijun
 * time: 2018/5/14 上午9:46
 * detail:选择地址页面
 */
public class ChooseCityActivity extends BaseActivity implements ChooseCityConstract.View {
    private ChooseCityPresent mPresent;
    @BindView(R.id.iv_refresh)
    ImageView ivRefresh;

    @BindView(R.id.gv_city)
    NoWrapGridView mGvCity;
    private BaseListViewAdapter mAdapter;
    private List<String> mList = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.model_activity_city;
    }

    @Override
    protected void initView() {
        setUpToolbar(R.mipmap.icon_nav_delete, "选择城市");

        startRefresh();

        mList.add("北京");
        mList.add("重庆");
        mList.add("上海");

        mAdapter = new BaseListViewAdapter(this, mList, R.layout.item_model_choosecity);
        mAdapter.setFactory(new IViewHolderFactory() {
            @Override
            public Object generatorViewHolder(View view, int pos) {
                return new TestViewHolder(view, pos);

            }
        });
        mGvCity.setAdapter(mAdapter);

    }



    private void startRefresh() {
        ivRefresh.setOnClickListener((v) -> {

            Animation mAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_refresh);
            LinearInterpolator mLinearInterpolator = new LinearInterpolator();
            mAnimation.setInterpolator(mLinearInterpolator);
            ivRefresh.startAnimation(mAnimation);
        });
    }

    @Override
    protected void errorReLoad() {

    }


    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }

    class TestViewHolder {

        @BindView(R.id.tv_test)
        TextView tvTest;

        public TestViewHolder(View view, int pos) {
            ButterKnife.bind(this, view);
            tvTest.setText(mList.get(pos));
        }

    }
}
