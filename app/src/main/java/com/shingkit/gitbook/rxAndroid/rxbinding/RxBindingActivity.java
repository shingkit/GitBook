package com.shingkit.gitbook.rxAndroid.rxbinding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.shingkit.gitbook.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by LENOVO on 2015/12/16.
 */
public class RxBindingActivity extends AppCompatActivity {

    @Bind(R.id.btn_rxbinding_1)
    Button btnRxBinding1;

    @Bind(R.id.tv_rxbinding)
    TextView tvBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_binding);
        ButterKnife.bind(this);
        
        RxView.clicks(btnRxBinding1).subscribe((ev) -> {
            tvBinding.setText("点击上面的按钮，我变啦！！！");
        });

        RxTextView.textChanges(tvBinding).subscribe((event) -> {
            Toast.makeText(RxBindingActivity.this, "文字变了，我也变！", Toast.LENGTH_SHORT).show();
        });


    }


}
