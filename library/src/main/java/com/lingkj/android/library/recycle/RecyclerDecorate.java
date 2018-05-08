package com.lingkj.android.library.recycle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author panlijun
 */

public class RecyclerDecorate extends RecyclerView.ItemDecoration {
    private int width;
    private int height;
    private int color;
    private final Rect mBounds = new Rect();
    private Drawable mDivider;
    private boolean transparent;
    private boolean mNeedLeftSpacing;
    public RecyclerDecorate() {
        super();
        mDivider=new ColorDrawable(Color.TRANSPARENT);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (parent.getLayoutManager() == null) {
            return;
        }
        if(!transparent) {
            draw(c, parent);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutManager manager=parent.getLayoutManager();
        if(manager instanceof GridLayoutManager){
            getGridOffset(outRect, view,parent,(GridLayoutManager) manager);
        }else{
            getLinerOffset(outRect,view,parent,manager);
        }
    }

    public void setColor(int color){
        this.color=color;
        mDivider=new ColorDrawable(color);
    }
    public void setTransparent(boolean transparent){
        this.transparent=transparent;
    }
    public void setDividerSize(int widht,int height){
       this.width=widht;
        this.height=height;
    }
    private void draw(Canvas canvas,RecyclerView parent) {
        if(transparent){
            return;
        }
        canvas.save();
        final int left=0;
        final int childCount = parent.getChildCount();
        final int right=parent.getWidth();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, mBounds);
            final int bottom = mBounds.bottom + Math.round(child.getTranslationY());
            final int top = bottom - height;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
        canvas.restore();
    }

    private void getGridOffset(Rect outRect,View child,RecyclerView parent,GridLayoutManager manager){
        int gridSize=manager.getSpanCount();
        int frameWidth = (int) ((parent.getWidth() - (float) width * (gridSize - 1)) / gridSize);
        int padding = parent.getWidth() / gridSize - frameWidth;
        int itemPosition = ((RecyclerView.LayoutParams) child.getLayoutParams()).getViewAdapterPosition();
        outRect.top = height;
//        if (itemPosition < gridSize) {
//            outRect.top = 0;
//        } else {
//            outRect.top = height;
//        }
        if (itemPosition % gridSize == 0) {
            outRect.left = 0;
            outRect.right = padding;
            mNeedLeftSpacing = true;
        } else if ((itemPosition + 1) % gridSize == 0) {
            mNeedLeftSpacing = false;
            outRect.right = 0;
            outRect.left = padding;
        } else if (mNeedLeftSpacing) {
            mNeedLeftSpacing = false;
            outRect.left = width - padding;
            if ((itemPosition + 2) % gridSize == 0) {
                outRect.right = width - padding;
            } else {
                outRect.right = width / 2;
            }
        } else if ((itemPosition + 2) % gridSize == 0) {
            mNeedLeftSpacing = false;
            outRect.left = width / 2;
            outRect.right = width - padding;
        } else {
            mNeedLeftSpacing = false;
            outRect.left = width / 2;
            outRect.right = width / 2;
        }
        outRect.bottom = 0;
    }

    private void getLinerOffset(Rect outRect,View child,RecyclerView parent,RecyclerView.LayoutManager manager){
        outRect.set(0,0,0,height);
    }
}
