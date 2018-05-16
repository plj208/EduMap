package com.lingkj.android.edumap.ui.index.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.facebook.stetho.common.LogUtil;
import com.lingkj.android.edumap.R;
import com.lingkj.android.edumap.base.BaseActivity;
import com.lingkj.android.edumap.ui.index.adapter.SortDetailAdapter;
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
    private SortDetailAdapter mAdapter;
    private List<String> mList = new ArrayList<>();
    private SortDetailFragment mSortDetailFragment;
    private int targetPosition;//点击左边某一个具体的item的位置
    private boolean isMoved;


    @Override
    protected int getLayoutId() {
        return R.layout.model_activity_sort;
    }

    LinearLayoutManager mManager;

    @Override
    protected void initView() {
        setUpToolbar(R.mipmap.icon_nav_delete, getResources().getString(R.string.sort_list));


        mManager = new LinearLayoutManager(this);
        mManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvSort.setLayoutManager(mManager);
        RecyclerDecorate decorate = new RecyclerDecorate();
        decorate.setDividerSize(0, SystemUtils.dpToPx(this, 1));
        decorate.setColor(getResources().getColor(R.color.line_bg));
        mRvSort.addItemDecoration(decorate);

        for (int i = 0; i < 100; i++) {
            mList.add("Test" + String.valueOf(i));
        }
        mAdapter=new SortDetailAdapter(SortActivity.this,mList,R.layout.item_model_sort);

        mAdapter.setFactory(new IViewHolderFactory<TestHolder>() {
            @Override
            public TestHolder generatorViewHolder(View view, BaseViewHolder.OnItemClickListener onItemClickListener) {
                return new TestHolder(view, onItemClickListener);
            }
        })
                .setOnItemClickListener(new BaseViewHolder.OnItemClickListener() {
            @Override
            public void onItemClicked(View v, int position) {

//                if (mSortDetailFragment != null) {
//                    if (mSortDetailFragment != null) {
//                        isMoved = true;
                        targetPosition = position;
                        setChecked(position, true);
//                    }
//                }
                createFragment();
//
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
        fragmentTransaction.replace(R.id.fl_fragment, mSortDetailFragment);
        fragmentTransaction.commit();
    }

    //将当前选中的item居中
    private void moveToCenter(int position) {
        //将点击的position转换为当前屏幕上可见的item的位置以便于计算距离顶部的高度，从而进行移动居中
        View childAt = mRvSort.getChildAt(position - mManager.findFirstVisibleItemPosition());
        if (childAt != null) {
            int y = (childAt.getTop() - mRvSort.getHeight() / 2);
            mRvSort.smoothScrollBy(0, y);
        }

    }

    private void setChecked(int position, boolean isLeft) {
//        Log.d("p-------->", String.valueOf(position));
        if (isLeft) {
            mAdapter.setCheckedPosition(position);
//            mSortAdapter.setCheckedPosition(position);
//            //此处的位置需要根据每个分类的集合来进行计算
//            int count = 0;
//            for (int i = 0; i < position; i++) {
//                count += mSortBean.getCategoryOneArray().get(i).getCategoryTwoArray().size();
//            }
//            count += position;
//            mSortDetailFragment.setData(count);
//            ItemHeaderDecoration.setCurrentTag(String.valueOf(targetPosition));//凡是点击左边，将左边点击的位置作为当前的tag
        } else {
//            if (isMoved) {
//                isMoved = false;
//            } else
//                mSortAdapter.setCheckedPosition(position);
//            ItemHeaderDecoration.setCurrentTag(String.valueOf(position));//如果是滑动右边联动左边，则按照右边传过来的位置作为tag
//
        }
        moveToCenter(position);

    }

    @Override
    protected void errorReLoad() {
    }


    class TestHolder extends BaseViewHolder<String> {

        @BindView(R.id.tv_left)
        TextView tvLeft;

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
