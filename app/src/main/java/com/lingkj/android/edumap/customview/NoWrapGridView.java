package com.lingkj.android.edumap.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * author: panlijun
 * time: 2018/5/14 上午11:29
 * detail:
 */
public class NoWrapGridView extends GridView {
    public NoWrapGridView(Context context) {
        super(context);
    }

    public NoWrapGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoWrapGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST));
    }
}
