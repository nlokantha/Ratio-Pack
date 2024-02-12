package com.example.ratiopack.SolidPack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.ratiopack.R;

public class Solid_PO_Activity extends AppCompatActivity {

    private TextView tv_name,tv_method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solid_po);
        initView();
        getAndSetIntent();




    }

    private void getAndSetIntent() {
        if (getIntent() != null && getIntent().hasExtra("buyer") && getIntent().hasExtra("method")){
            tv_name.setText(getIntent().getStringExtra("buyer"));
            tv_method.setText(getIntent().getStringExtra("method"));
        }else {
            tv_name.setText("N/A");
            tv_method.setText("N/A");
        }

    }

    private void initView() {
        tv_name=findViewById(R.id.tv_name);
        tv_method=findViewById(R.id.tv_method);
    }
}