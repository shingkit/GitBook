package com.shingkit.gitbook;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * 注解支持库
 */
public class AnnoActivity extends AppCompatActivity {

    @StringRes
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anno);
        Toast.makeText(AnnoActivity.this, "str:"+str, Toast.LENGTH_SHORT).show();
    }
}
