package com.lingkj.android.edumap.ui.index.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.facebook.stetho.common.LogUtil;
import com.lingkj.android.edumap.R;
import com.lingkj.android.edumap.base.BaseActivity;
import com.lingkj.android.edumap.ui.index.fragment.SortDetailFragment;
import com.lingkj.android.edumap.utils.SystemUtils;
import com.lingkj.android.edumap.utils.ToastUtils;
import com.lingkj.android.library.recycle.BaseRecycleAdapter;
import com.lingkj.android.library.recycle.BaseViewHolder;
import com.lingkj.android.library.recycle.IViewHolderFactory;
import com.lingkj.android.library.recycle.RecyclerDecorate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: panlijun
 * time: 2018/5/14 下午5:28
 * detail:全部分类页面
 */
public class SortActivity extends BaseActivity {
    @BindView(R.id.rv_sort)
    RecyclerView mRvSort;
    private BaseRecycleAdapter<String, TestHolder> mAdapter;
    private List<String> mList = new ArrayList<>();
    private  SortDetailFragment mSortDetailFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.model_activity_sort;
    }

    @Override
    protected void initView() {
        setUpToolbar(R.mipmap.icon_nav_delete, getResources().getString(R.string.sort_list));


        LinearLayoutManager mManager=new LinearLayoutManager(this);
        mManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvSort.setLayoutManager(mManager);
        RecyclerDecorate decorate = new RecyclerDecorate();
        decorate.setDividerSize(0, SystemUtils.dpToPx(this, 1));
        decorate.setColor(getResources().getColor(R.color.line_bg));
        mRvSort.addItemDecoration(decorate);

        for (int i = 0; i < 100; i++) {
            mList.add("Test" + String.valueOf(i));
        }
        mAdapter=new BaseRecycleAdapter<>(mList,R.layout.item_model_sort);

        mAdapter.setFactory(new IViewHolderFactory<TestHolder>() {
            @Override
            public TestHolder generatorViewHolder(View view, BaseViewHolder.OnItemClickListener onItemClickListener) {
                return new TestHolder(view, onItemClickListener);
            }
        }).setOnItemClickListener(new BaseViewHolder.OnItemClickListener() {
            @Override
            public void onItemClicked(View v, int position) {

            }
        });
        mRvSort.setAdapter(mAdapter);



    }



    public void createFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        mSortDetailFragment = new SortDetailFragment();
        Bundle bundle = new Bundle();
//        bundle.putParcelableArrayList("right", mSortBean.getCategoryOneArray());
        mSortDetailFragment.setArguments(bundle);
//        mSortDetailFragment.setListener(this);
        fragmentTransaction.add(R.id.fl_fragment, mSortDetailFragment);
        fragmentTransaction.commit();
    }



    @Override
    protected void errorReLoad() {
    }


    class TestHolder extends BaseViewHolder<String> {

        @BindView(R.id.tv_left)
        CheckBox tvLeft;

        public TestHolder(View itemView, OnItemClickListener onItemClickedListener) {
            super(itemView, onItemClickedListener);
            ButterKnife.bind(this, itemView);


        }

        @Override
        public void setData(String s) {
            tvLeft.setText(s);

        }
    }
}
