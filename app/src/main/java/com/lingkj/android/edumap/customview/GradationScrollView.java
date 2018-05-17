package com.lingkj.android.edumap.customview;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

/**
 * author: panlijun
 * time: 2018/5/15 下午4:13
 * detail:滚动监听
 */
public class GradationScrollView extends NestedScrollView {


    private int downX;

    private int downY;

    private int mTouchSlop;

    public interface ScrollViewListener {

        void onScrollChanged(GradationScrollView scrollView, int x, int y,
                             int oldx, int oldy);

    }

    private ScrollViewListener scrollViewListener = null;

    public GradationScrollView(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public GradationScrollView(Context context, AttributeSet attrs,
                               int defStyle) {
        super(context, attrs, defStyle);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public GradationScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }


//    @Override
//
//    public boolean onInterceptTouchEvent(MotionEvent e) {
//
//        int action = e.getAction();
//
//        switch (action) {
//
//            case MotionEvent.ACTION_DOWN:
//
//                downX = (int) e.getRawX();
//
//                downY = (int) e.getRawY();
//
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//
//                int moveY = (int) e.getRawY();
//
//                if (Math.abs(moveY - downY) > mTouchSlop) {
//
//                    return true;
//
//                }
//
//        }
//
//        return super.onInterceptTouchEvent(e);
//
//    }


}

