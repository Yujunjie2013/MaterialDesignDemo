package com.example.yune.materialdesigndemo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class BottomSheetActivity extends AppCompatActivity {

    private static final String TAG = "yujj";

    public static void startA(Context context) {
        context.startActivity(new Intent(context, BottomSheetActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);

        setSwipeBehavior();

        View view = findViewById(R.id.share_view);
        final BottomSheetBehavior<View> sheetBehavior = BottomSheetBehavior.from(view);
        //设置折叠时的高度
        //sheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);

        //监听BottomSheetBehavior 状态的变化
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        sheetBehavior.setHideable(true);
        findViewById(R.id.btn_show_bottom_sheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            }
        });
    }

    private void setSwipeBehavior() {
        View mSwipeLayout = findViewById(R.id.tv);
        SwipeDismissBehavior swipe = new SwipeDismissBehavior();

        /**
         * //设置滑动的方向，有3个值
         *
         * 1，SWIPE_DIRECTION_ANY 表示向左像右滑动都可以，
         * 2，SWIPE_DIRECTION_START_TO_END，只能从左向右滑
         * 3，SWIPE_DIRECTION_END_TO_START，只能从右向左滑
         */
        swipe.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_START_TO_END);

        swipe.setStartAlphaSwipeDistance(0f);

        swipe.setSensitivity(0.2f);

        swipe.setListener(new SwipeDismissBehavior.OnDismissListener() {
            @Override
            public void onDismiss(View view) {
                Log.e(TAG,"------>onDissmiss");
            }

            @Override
            public void onDragStateChanged(int state) {
                Log.e(TAG,"------>onDragStateChanged");
            }
        });

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) mSwipeLayout.getLayoutParams();
        if(layoutParams!=null){
            layoutParams.setBehavior(swipe);
        }

    }
}
