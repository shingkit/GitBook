package com.shingkit.gitbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shingkit.gitbook.rxAndroid.RxAndroidActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.lambda)
    public void navigate2Lambda() {
        startActivity(new Intent(this, LambdaActivity.class));
    }

    @OnClick(R.id.rxandroid)
    public void navigate2rxAndroid() {
        startActivity(new Intent(this, RxAndroidActivity.class));
    }
}
