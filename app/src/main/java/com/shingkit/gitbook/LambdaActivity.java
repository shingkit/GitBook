package com.shingkit.gitbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LambdaActivity extends AppCompatActivity {

    @Bind(R.id.btn1)
    Button btn1;
    @Bind(R.id.btn2)
    Button btn2;
    @Bind(R.id.btn3)
    Button btn3;

    @Bind(R.id.list)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lambda);
        ButterKnife.bind(this);
        btn1.setOnClickListener((View v) -> Toast.makeText(LambdaActivity.this, "WTF1", Toast.LENGTH_SHORT).show());

        btn2.setOnClickListener(v -> {
            Toast.makeText(LambdaActivity.this, "WTF2", Toast.LENGTH_SHORT).show();
        });
        //System.out: android.support.v7.widget.AppCompatButton{5318529 VFED..CL ...P.... 0,288-264,432 #7f0c0052 app:id/btn3}
        btn3.setOnClickListener(System.out::println);

        List<String> list = getMyString();
        btn3.setOnLongClickListener(v -> {
            Toast.makeText(LambdaActivity.this, "WTF3", Toast.LENGTH_SHORT).show();
            return true;
        });


        listView.setAdapter(
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getMyString())
        );
        listView.setOnItemClickListener((parent,view,position,id)->{
            Toast.makeText(LambdaActivity.this, getMyString().get(position), Toast.LENGTH_SHORT).show();
        });
    }

    private List<String> getMyString() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++)
            list.add("item " + i);
        return list;
    }

}
