package com.shingkit.gitbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LambdaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lambda);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener((View v) -> Toast.makeText(LambdaActivity.this, "WTF", Toast.LENGTH_SHORT).show());
    }
}
