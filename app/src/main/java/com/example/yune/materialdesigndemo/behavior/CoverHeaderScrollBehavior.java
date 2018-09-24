package com.example.yune.materialdesigndemo.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.yune.materialdesigndemo.R;

public class CoverHeaderScrollBehavior extends CoordinatorLayout.Behavior<View> {
    public static final String TAG = "CoverHeaderScroll";
    private final Context context;

    public CoverHeaderScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
    }

    //重写这个方法对子View 进行重新布局
    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
//        Log.i(TAG, "onLayoutChild.....");
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        if (params != null && params.height == CoordinatorLayout.LayoutParams.MATCH_PARENT) {
            child.layout(0, 0, parent.getWidth(), parent.getHeight());
            child.setTranslationY(getHeaderHeight());
            return true;
        }

        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int nestedScrollAxes, int type) {
//        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
//        int i = 4 & ViewCompat.SCROLL_AXIS_VERTICAL;与运算取得是相同位比较，同为1时结果为1
//        Log.e(TAG, "nestedScrollAxes-->" + nestedScrollAxes);
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes, type);
    }

    //嵌套滚动发生之前被调用
    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
//        Log.w(TAG, "dy:" + dy + "---type:" + type);
        // 在这个方法里面只处理向上滑动
        if (dy < 0 || child.getTranslationY() == 0) {
            return;
        }

        float transY = child.getTranslationY() - dy;
//        Log.i(TAG, "transY:" + transY + "++++child.getTranslationY():" + child.getTranslationY() + "---->dy:" + dy);
        if (transY > 0) {
            child.setTranslationY(transY);
            consumed[1] = dy;
        } else if (child.getTranslationY() >= 0 && transY <= 0) {

            child.setTranslationY(0);
            consumed[1] = (int) child.getTranslationY();
        }
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
        // 在这个方法里只处理向下滑动
        if (dyUnconsumed > 0) {
            return;
        }

        float transY = child.getTranslationY() - dyUnconsumed;
//        Log.i(TAG, "------>transY:" + transY + "****** child.getTranslationY():" + child.getTranslationY() + "--->dyUnconsumed" + dxUnconsumed);
        if (transY > 0 && transY < getHeaderHeight()) {
            child.setTranslationY(transY);
        } else if (transY >= getHeaderHeight() && child.getTranslationY() <= getHeaderHeight()) {
            child.setTranslationY(getHeaderHeight());
        }
    }

    //用户松开手指，并在惯性调用之前
    @Override
    public boolean onNestedPreFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, float velocityX, float velocityY) {
//        Log.e(TAG, "onNestedPreFlingxxxxxxxxxxxxxxxxxxxxxxxxxvelocityX:" + velocityX + "----------->velocityY:" + velocityY);
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }

    //嵌套滚动结束
    @Override
    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int type) {
        Log.d(TAG, "onStopNestedScroll``````````````````:type" + type + "Y:" + child.getTranslationY());
//        super.onStopNestedScroll(coordinatorLayout, child, target, type);
//        if (type != 1 || child.getTranslationY() == 0 || child.getTranslationY() == getHeaderHeight()) {
//            return;
//        }
//        if (type != 1) return;
//        float translationY = child.getTranslationY();
//        int headerHeight = getHeaderHeight();
//        int head = headerHeight / 2;
//        if (translationY > 0 && translationY < head) {
//            int duration = (int) (head / translationY * 100);
//            child.animate().translationY(0).setDuration(duration).start();
//        } else if (translationY >= head && translationY < headerHeight) {
//            int duration = (int) (headerHeight / translationY * 100);
//            child.animate().translationY(headerHeight).setDuration(duration).start();
//        }

    }

    /**
     * 获取Header 高度
     *
     * @return
     */
    public int getHeaderHeight() {
        int height = context.getResources().getDimensionPixelOffset(R.dimen.header_height);
        Log.d(TAG, "高度：" + height);
        return height;
    }
}
