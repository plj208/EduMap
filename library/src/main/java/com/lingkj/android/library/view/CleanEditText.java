package com.lingkj.android.library.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.lingkj.android.adapterlibrary.R;


/**
 * @author panlijun
 */
public class CleanEditText extends AppCompatEditText implements TextWatcher{
    Drawable mClearDrawable;
    public CleanEditText(Context context) {
        this(context,null);
    }

    public CleanEditText(Context context, AttributeSet attrs) {
        //super(context,attrs);
        this(context,attrs, android.support.v7.appcompat.R.attr.editTextStyle);
    }

    public CleanEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDrawable(context);
        addTextChangedListener(this);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        setClearIconVisible(!TextUtils.isEmpty(s));
    }


    private void initDrawable(Context context){
        mClearDrawable=getCompoundDrawables()[2];
        if(mClearDrawable==null){
            mClearDrawable=context.getResources().getDrawable(R.drawable.library_clean);
            mClearDrawable.setBounds(0,0,mClearDrawable.getMinimumWidth(),mClearDrawable.getMinimumHeight());
            //setCompoundDrawables(getCompoundDrawables()[0],getCompoundDrawables()[1],mClearDrawable,getCompoundDrawables()[3]);
        }
    }
    protected void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {

                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
                        && (event.getX() < ((getWidth() - getPaddingRight())));

                if (touchable) {
                    this.setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if(focused){
            if(!TextUtils.isEmpty(getText()))
                setClearIconVisible(true);
        }else{
            setClearIconVisible(false);
        }
    }
}
