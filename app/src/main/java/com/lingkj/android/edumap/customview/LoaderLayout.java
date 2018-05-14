package com.lingkj.android.edumap.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lingkj.android.edumap.R;
import com.lingkj.android.edumap.utils.SystemUtils;
import com.lingkj.android.library.commonutils.NetWorkUtils;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * author: panlijun
 * time: 2018/5/14 上午10:25
 * detail:
 */
public class LoaderLayout extends FrameLayout implements View.OnClickListener {

    public enum LoaderState {
        State_None, //初始化状态
        State_Loading, //正在加载状态
        State_Error, //重加载状态
        State_NoData //无数据状态
    }

    public interface OnReloadListener {
        void onReload();
    }

    //    private Activity activity;
    private Context context;
    private View contentView;
    private RelativeLayout loadingLayout;
    private ImageView imgTip; //加载图片提示
//    private AVLoadingIndicatorView progressBar;
    private TextView txtLoaderInfo; //加载信息
    private TextView btnReload; //重新加载数据
    private boolean isAddIndictor = false;//是否添加了指示器
    private OnReloadListener listener;//重加载监听器
    private LoaderState loaderState = LoaderState.State_None;//加载器状态
    private AtomicBoolean completedTag = new AtomicBoolean(false); //是否完成加载标识
    private Drawable loadingDrawable, loadErrDrawable, loadNoDataDrawable; //图片的几种状态值
    private String loadingText, loadErrText, loadNoDataText; //提示文字

    public LoaderLayout(Context context) {
        super(context);
        initLoaderLayout(context, null);
    }

    public LoaderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLoaderLayout(context, attrs);
    }

    public LoaderLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLoaderLayout(context, attrs);
    }

    private void initLoaderLayout(Context context, AttributeSet attr) {
        this.context = context;
        if (attr != null) {
            TypedArray ta = this.context.obtainStyledAttributes(attr, R.styleable.LoaderLayout);
            loadingDrawable = ta.getDrawable(R.styleable.LoaderLayout_LoadingImage);
            loadErrDrawable = ta.getDrawable(R.styleable.LoaderLayout_LoadErrImage);
            loadNoDataDrawable = ta.getDrawable(R.styleable.LoaderLayout_LoadNoDataImage);
            loadingText = ta.getString(R.styleable.LoaderLayout_LoadingText);
            loadErrText = ta.getString(R.styleable.LoaderLayout_LoadErrText);
            loadNoDataText = ta.getString(R.styleable.LoaderLayout_LoadNoDataText);
            ta.recycle();
        }
        if (TextUtils.isEmpty(loadingText))
            loadingText = "拼命加载中...";
        if (TextUtils.isEmpty(loadErrText))
            loadErrText = "页面加载失败，点击重新加载";
        if (TextUtils.isEmpty(loadNoDataText))
            loadNoDataText = "暂无数据，点击重新获取";
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int childCount = getChildCount();
        if (childCount >= 2 && !isAddIndictor) {
            isAddIndictor = true;
            boolean isEditMode = isInEditMode();
            //设置loadingLayout
            loadingLayout = (RelativeLayout) getChildAt(0);
            loadingLayout.setGravity(Gravity.CENTER);
//            loadingLayout.setBackgroundColor(Color.WHITE);
            loadingLayout.setVisibility(isEditMode ? View.GONE : View.VISIBLE);
            FrameLayout.LayoutParams loadingLayoutParams = (FrameLayout.LayoutParams) loadingLayout.getLayoutParams();
            loadingLayoutParams.gravity = Gravity.CENTER;
            loadingLayout.setLayoutParams(loadingLayoutParams);
            //设置进度条
//            progressBar = (AVLoadingIndicatorView) loadingLayout.findViewById(R.id.pbLoading);
//            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) progressBar.getLayoutParams();
//            lp.rightMargin = DensityUtil.dip2px(context, 5f);
//            lp.width = DensityUtil.dip2px(context, 60f);
//            lp.height = DensityUtil.dip2px(context, 18f);
//            progressBar.setLayoutParams(lp);
//            progressBar.setVisibility(isEditMode ? View.GONE : View.VISIBLE);
            //设置进度文字
//            txtLoaderInfo = (TextView) loadingLayout.findViewById(R.id.txtLoaderInfo);
//            txtLoaderInfo.setText(loadingText);
//            txtLoaderInfo.setTextColor(Color.parseColor("#ff333333"));
//            txtLoaderInfo.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14f);
//            txtLoaderInfo.setVisibility(isEditMode ? View.GONE : View.VISIBLE);
//            txtLoaderInfo.setLinksClickable(true);
//            txtLoaderInfo.setMovementMethod(LinkMovementMethod.getInstance());
//            btnReload = (TextView) loadingLayout.findViewById(R.id.btnReload);
//            btnReload.setOnClickListener(this);
//            imgTip = (ImageView) loadingLayout.findViewById(R.id.imgTip);
//            if (loadingDrawable != null) {
//                imgTip.setImageDrawable(loadingDrawable);
//                imgTip.setVisibility(View.VISIBLE);
//            } else {
//                imgTip.setVisibility(View.GONE);
//            }
//            if (isEditMode) loadingLayout.setVisibility(View.GONE);
//            //设置ContentView
//            contentView = getChildAt(1);
//            contentView.setVisibility(isEditMode ? View.VISIBLE : View.GONE);
//            setLoaderState(isEditMode || completedTag.get() ? LoaderState.State_None : LoaderState.State_Loading);
        }
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            setLoaderState(LoaderState.State_Loading);
            listener.onReload();
        }
    }

    /**
     * 设置加载器状态
     *
     * @param state 状态值
     */
    public void setLoaderState(LoaderState state) {
        if (isAddIndictor) {
            switch (state) {
                case State_None:
                    if (loadingLayout.getVisibility() != View.GONE)
                        loadingLayout.setVisibility(View.GONE);
//                    if (progressBar.getVisibility() != View.GONE)
//                        progressBar.setVisibility(View.GONE);
                    txtLoaderInfo.setText("");
                    if (btnReload.getVisibility() != View.GONE)
                        btnReload.setVisibility(View.GONE);
                    btnReload.setEnabled(false);
                    if (imgTip.getVisibility() != View.GONE)
                        imgTip.setVisibility(View.GONE);
                    if (contentView != null && contentView.getVisibility() != View.VISIBLE)
                        contentView.setVisibility(View.VISIBLE);
                    break;
                case State_Loading:
                    if (btnReload.getVisibility() != View.GONE)
                        btnReload.setVisibility(View.GONE);
                    btnReload.setEnabled(false);
                    txtLoaderInfo.setText(Html.fromHtml(loadingText));
                    if (loadingDrawable != null) {
                        imgTip.setImageDrawable(loadingDrawable);
                        if (imgTip.getVisibility() != View.VISIBLE)
                            imgTip.setVisibility(View.VISIBLE);
                    } else {
                        if (imgTip.getVisibility() != View.GONE)
                            imgTip.setVisibility(View.GONE);
                    }
//                    if (progressBar.getVisibility() != View.VISIBLE)
//                        progressBar.setVisibility(View.VISIBLE);
                    if (loadingLayout.getVisibility() != View.VISIBLE)
                        loadingLayout.setVisibility(View.VISIBLE);
                    if (contentView != null && contentView.getVisibility() != View.GONE)
                        contentView.setVisibility(View.GONE);
                    break;
                case State_Error:
                    boolean isNetworkAvailable = NetWorkUtils.isNetConnected(getContext());
                    String tipInfomationText = isNetworkAvailable ? loadErrText : "网络异常，请检查网络设置后刷新";
                    SpannableString tipInfoSpannable = new SpannableString(tipInfomationText);
                    if (!isNetworkAvailable) {
                        tipInfoSpannable.setSpan(new ClickableSpan() { //设置网络设置点击事件
                            @Override
                            public void onClick(View widget) {
                                SystemUtils.openNetworkSettingsPage(context);
                            }

                            @Override
                            public void updateDrawState(TextPaint ds) {
                                super.updateDrawState(ds);
                                ds.setColor(SystemUtils.getColor(context, R.color.colorPrimary));
                                ds.setUnderlineText(false);
                            }
                        }, 8, 12, SpannableString.SPAN_INCLUSIVE_INCLUSIVE);
                    }
                    txtLoaderInfo.setText(tipInfoSpannable);
                    if (btnReload.getVisibility() != View.VISIBLE)
                        btnReload.setVisibility(View.VISIBLE);
                    btnReload.setEnabled(true);
//                    if (progressBar.getVisibility() != View.GONE)
//                        progressBar.setVisibility(View.GONE);
                    if (loadErrDrawable != null) {
                        imgTip.setImageDrawable(loadErrDrawable);
                        if (imgTip.getVisibility() != View.VISIBLE)
                            imgTip.setVisibility(View.VISIBLE);
                    } else {
                        if (imgTip.getVisibility() != View.GONE)
                            imgTip.setVisibility(View.GONE);
                    }
                    if (loadingLayout.getVisibility() != View.VISIBLE)
                        loadingLayout.setVisibility(View.VISIBLE);
                    if (contentView != null && contentView.getVisibility() != View.GONE)
                        contentView.setVisibility(View.GONE);
                    break;
                case State_NoData:
                    if (btnReload.getVisibility() != View.VISIBLE)
                        btnReload.setVisibility(View.VISIBLE);
                    btnReload.setEnabled(true);
                    txtLoaderInfo.setText(Html.fromHtml(loadNoDataText));
                    if (loadNoDataDrawable != null) {
                        imgTip.setImageDrawable(loadNoDataDrawable);
                        if (imgTip.getVisibility() != View.VISIBLE)
                            imgTip.setVisibility(View.VISIBLE);
                    } else {
                        if (imgTip.getVisibility() != View.GONE)
                            imgTip.setVisibility(View.GONE);
                    }
//                    if (progressBar.getVisibility() != View.GONE)
//                        progressBar.setVisibility(View.GONE);
                    if (loadingLayout.getVisibility() != View.VISIBLE)
                        loadingLayout.setVisibility(View.VISIBLE);
                    if (contentView != null && contentView.getVisibility() != View.GONE)
                        contentView.setVisibility(View.GONE);
                    break;
            }
            loaderState = state;
        } else {
            if (state != LoaderState.State_Loading) {
                completedTag.set(true);
            }
        }
    }

    /**
     * 获取加载状态
     *
     * @return
     */

    public LoaderState getLoaderState() {
        return loaderState;
    }

    /**
     * 获取重新加载的监听器
     *
     * @return
     */
    public OnReloadListener getOnReloadListener() {
        return listener;
    }

    /**
     * 设置重新加载的监听器
     *
     * @param listener 重新加载的数据监听器
     */
    public void setOnReloadListener(OnReloadListener listener) {
        this.listener = listener;
    }

}
