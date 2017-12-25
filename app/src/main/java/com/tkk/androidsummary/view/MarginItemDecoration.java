package com.tkk.androidsummary.view;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created  on 2017/11/8
 *
 * @author 唐开阔
 * @describe  RecyclerView空白ItemDecoration

 */

public class MarginItemDecoration extends RecyclerView.ItemDecoration {
    int mSpace;//间距
    int gridItemNums = -1;  //每一行默认的item数，默认-1，单行
    boolean  isModeHorizontal  = false;

    /**
     * 竖直布局
     * @param space
     */
    public MarginItemDecoration(int space) {
        this.mSpace = space;
    }

    /**
     * 水平布局
     * @param mSpace
     * @param isModeHorizontal
     */
    public MarginItemDecoration(int mSpace, boolean isModeHorizontal) {
        this.mSpace = mSpace;
        this.isModeHorizontal = isModeHorizontal;
    }

    /**
     * 网格布局
     * @param space
     * @param gridItemNums
     */
    public MarginItemDecoration(int space, int gridItemNums) {
        this.mSpace = space;
        this.gridItemNums = gridItemNums;
    }

    /**
     * 可以理解为padding效果
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildAdapterPosition(view);
        //水平布局
        if (isModeHorizontal){
            outRect.left = mSpace;
            outRect.right = mSpace;
            return;
        }

        //普通的列表模式,上下左右一样
        if (gridItemNums == -1) {
            if (pos == 0) {
                outRect.bottom = mSpace / 2;
                outRect.top = mSpace;
            } else if (pos == parent.getAdapter().getItemCount() - 1) {
                outRect.bottom = mSpace;
                outRect.top = mSpace / 2;
            } else {
                outRect.bottom = mSpace / 2;
                outRect.top = mSpace / 2;
            }
            outRect.left = mSpace;
            outRect.right = mSpace;
            //网格模式,上下左右一样
        } else {
            //第一行
            if (pos < gridItemNums) {
                outRect.bottom = mSpace / 2;
                outRect.top = mSpace;//最后一行
            } else if (pos + gridItemNums >= parent.getAdapter().getItemCount()) {
                //最后一行
                outRect.bottom = mSpace;
                outRect.top = mSpace / 2;
            } else { //中间的
                outRect.bottom = mSpace / 2;
                outRect.top = mSpace / 2;
            }

            if (pos % gridItemNums == 0) { //第一列
                outRect.left = mSpace;
                outRect.right = mSpace / 2;
            } else if (pos % gridItemNums == gridItemNums - 1) {//最后一列
                outRect.left = mSpace / 2;
                outRect.right = mSpace;
            } else {//中间
                outRect.left = mSpace / 2;
                outRect.right = mSpace / 2;
            }

        }


    }

    /**
     * 绘制在内容下面-应用于分割线
     * 绘制区域只能是getItemOffsets返回的矩形区域
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    /**
     * 绘制在内容之上，应用于商品加上一个标签，比如“推荐”，“热卖”，“秒杀”等等
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
