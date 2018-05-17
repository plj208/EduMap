package com.lingkj.android.edumap.ui.index.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.githang.statusbar.StatusBarCompat;
import com.lingkj.android.edumap.MainActivity;
import com.lingkj.android.edumap.R;
import com.lingkj.android.edumap.base.BaseFragment;
import com.lingkj.android.edumap.base.basemvp.BaseView;
import com.lingkj.android.edumap.bean.IndexBigSort;
import com.lingkj.android.edumap.bean.IndexClass;
import com.lingkj.android.edumap.customview.FullyGridLayoutManager;
import com.lingkj.android.edumap.customview.FullyLinearLayoutManager;
import com.lingkj.android.edumap.customview.GradationScrollView;
import com.lingkj.android.edumap.ui.index.activity.IndexActivity;
import com.lingkj.android.edumap.ui.index.adapter.ClassAdapter;
import com.lingkj.android.edumap.ui.index.adapter.SortDetailAdapter;
import com.lingkj.android.edumap.utils.GlideImageLoader;
import com.lingkj.android.edumap.utils.SystemUtils;
import com.lingkj.android.edumap.utils.ToolBarUtils;
import com.lingkj.android.library.recycle.BaseRecycleAdapter;
import com.lingkj.android.library.recycle.BaseViewHolder;
import com.lingkj.android.library.recycle.IViewHolderFactory;
import com.lingkj.android.library.recycle.RecyclerDecorate;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: panlijun
 * time: 2018/5/10 上午11:27
 * detail:首页
 */
public class IndexFragment extends BaseFragment {

    @BindView(R.id.sp_refresh)
    SmartRefreshLayout mRefresh;

    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_location)
    ImageView ivLocation;
    @BindView(R.id.sv_scroll)
    GradationScrollView mScrollView;
//    @BindView(R.id.sv_search)
//    SearchView mSearchView;
    private int height;

    @BindView(R.id.bn_img)
    Banner mBanner;
    @BindView(R.id.xv_big_sort)
    RecyclerView mBigSort;
    private BaseRecycleAdapter<IndexBigSort, BigSortHolder> mBigAdapter;
    private List<IndexBigSort> mBigSortList = new ArrayList<>();
    private Integer[] mBigSortImgs = {R.mipmap.index_big_sort_icon1, R.mipmap.index_big_sort_icon2,
            R.mipmap.index_big_sort_icon3, R.mipmap.index_big_sort_icon3, R.mipmap.index_big_sort_icon5};

    @BindView(R.id.xv_small_sort)
    RecyclerView mRecyclerSmall;
    private BaseRecycleAdapter<IndexBigSort, BigSortHolder> mSmallAdapter;
    private List<IndexBigSort> mSmallSortList = new ArrayList<>();
    private Integer[] mSmallSortImgs = {
            R.mipmap.index_small_sort_icon1, R.mipmap.index_small_sort_icon2,
            R.mipmap.index_small_sort_icon3, R.mipmap.index_small_sort_icon4,
            R.mipmap.index_small_sort_icon5, R.mipmap.index_small_sort_icon6,
            R.mipmap.index_small_sort_icon7, R.mipmap.index_small_sort_icon8,
            R.mipmap.index_small_sort_icon9, R.mipmap.index_small_sort_icon10,
    };

    private List<Integer> mImgs = new ArrayList<>();


    @BindView(R.id.xv_class)
    RecyclerView mRecyclerViewClass;
    private ClassAdapter mClassAdapter;
    private List<IndexClass> mIndexClassList = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.model_fragment_index;
    }


    @Override
    protected void initView() {

        mRefresh.getRefreshFooter().setLoadmoreFinished(true);
        ((IndexActivity) getContext()).getToolbar().setVisibility(View.GONE);
        mImgs.add(R.mipmap.ic_launcher);
        mImgs.add(R.mipmap.ic_launcher);
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(mImgs);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();

        scrollStyle();
        setSortData();
        loadClassData();

    }

    private void scrollStyle() {
        StatusBarCompat.setStatusBarColor(getActivity(), getResources().getColor(R.color.black_333333));
        ViewTreeObserver vto = mBanner.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llTitle.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height = mBanner.getHeight();

                mScrollView.setScrollViewListener(new GradationScrollView.ScrollViewListener() {
                    @Override
                    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
                        if (y <= 0) {   //设置标题的背景颜色
                            llTitle.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));
                            StatusBarCompat.setStatusBarColor(getActivity(), getResources().getColor(R.color.black_333333));
                            tvTitle.setTextColor(getResources().getColor(R.color.white));
                            ivLocation.setImageResource(R.mipmap.icon_location_defalut);
//                            mSearchView.setBackground(R.drawable.index_search_style);

                        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
                            float scale = (float) y / height;
                            float alpha = (255 * scale);
                            tvTitle.setTextColor(Color.argb((int) alpha, 1, 24, 28));
                            llTitle.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                        } else {    //滑动到banner下面设置普通颜色
                            llTitle.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
                            StatusBarCompat.setStatusBarColor(getActivity(), getResources().getColor(R.color.white));
                            tvTitle.setTextColor(getResources().getColor(R.color.black));
                            ivLocation.setImageResource(R.mipmap.index_location_scroll);
//                            mSearchView.setBackgroundResource(R.drawable.index_search_style);


                        }
                    }
                });
            }
        });

    }


    private void setSortData() {

        String[] mBigSortArray = getResources().getStringArray(R.array.index_text_big_sort);
        for (int i = 0; i < mBigSortImgs.length; i++) {
            IndexBigSort mBigSort = new IndexBigSort();
            mBigSort.setName(mBigSortArray[i]);
            mBigSort.setResId(mBigSortImgs[i]);
            mBigSortList.add(mBigSort);
        }
        mBigAdapter = new BaseRecycleAdapter<>(mBigSortList, R.layout.item_index_bigsort);
        mBigAdapter.setFactory((View view, BaseViewHolder.OnItemClickListener onItemClickListener) -> {
            return new BigSortHolder(view, onItemClickListener);
        });
        mBigSort.setAdapter(mBigAdapter);


        FullyGridLayoutManager fullyGridLayoutManager = new FullyGridLayoutManager(getActivity(), 5);
        mRecyclerSmall.setLayoutManager(fullyGridLayoutManager);
        String[] mSmallSortArray = getResources().getStringArray(R.array.index_text_small_sort);
        for (int i = 0; i < mSmallSortArray.length; i++) {
            IndexBigSort mSmallData = new IndexBigSort();
            mSmallData.setName(mSmallSortArray[i]);
            mSmallData.setResId(mSmallSortImgs[i]);
            mSmallSortList.add(mSmallData);
        }


        mSmallAdapter = new BaseRecycleAdapter<>(mSmallSortList, R.layout.item_index_smallsort);
        mSmallAdapter.setFactory((View view, BaseViewHolder.OnItemClickListener onItemClickListener) -> {
            return new BigSortHolder(view, onItemClickListener);
        });
        mRecyclerSmall.setAdapter(mSmallAdapter);


    }

    private void loadClassData() {

        FullyLinearLayoutManager fullyLinearLayoutManager = new FullyLinearLayoutManager(getActivity());
        fullyLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewClass.setLayoutManager(fullyLinearLayoutManager);
        for (int i = 0; i < 5; i++) {
            IndexClass indexClass = new IndexClass();
            indexClass.setCourge("" + i);
            mIndexClassList.add(indexClass);
        }
        mClassAdapter = new ClassAdapter(getActivity(), mIndexClassList);
        mRecyclerViewClass.setAdapter(mClassAdapter);


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


    class BigSortHolder extends BaseViewHolder<IndexBigSort> {

        @BindView(R.id.iv_sort)
        ImageView ivSort;
        @BindView(R.id.tv_sort)
        TextView tvSort;

        public BigSortHolder(View itemView, OnItemClickListener onItemClickedListener) {
            super(itemView, onItemClickedListener);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(IndexBigSort o) {
            Glide.with(getActivity()).load(o.getResId()).into(ivSort);
            tvSort.setText(o.getName());

        }
    }


    class ClassHolder extends BaseViewHolder<IndexClass> {

        public ClassHolder(View itemView, OnItemClickListener onItemClickedListener) {
            super(itemView, onItemClickedListener);
        }

        @Override
        public void setData(IndexClass indexClass) {

        }
    }
}
