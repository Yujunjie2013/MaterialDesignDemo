package com.example.yune.materialdesigndemo;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.fl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetActivity.startA(MainActivity.this);
            }
        });
        findViewById(R.id.fl_cover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoverHeaderActivity.startA(MainActivity.this);
            }
        });

    }
}
