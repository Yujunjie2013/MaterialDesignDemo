package com.example.yune.materialdesigndemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CoverHeaderActivity extends AppCompatActivity {

    public static void startA(Context context) {
        context.startActivity(new Intent(context, CoverHeaderActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover_header);
    }
}
