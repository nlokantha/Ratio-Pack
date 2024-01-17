package com.example.ratiopack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    Button btn_back,btn_scan,btn_setup;
    TextView tv_name,tv_method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initView();

        if (getIntent() != null && getIntent().hasExtra("buyer") && getIntent().hasExtra("method")){
                tv_name.setText(getIntent().getStringExtra("buyer"));
                tv_method.setText(getIntent().getStringExtra("method"));
        } else if (getIntent() != null && getIntent().hasExtra("b") && getIntent().hasExtra("m")) {
            tv_name.setText(getIntent().getStringExtra("b"));
            tv_method.setText(getIntent().getStringExtra("m"));
        }

        btn_setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, SetupActivity.class);
                intent.putExtra("buyer",tv_name.getText().toString());
                intent.putExtra("method",tv_method.getText().toString());
                startActivity(intent);
            }
        });

        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MenuActivity.this, ListActivity.class);
                intent.putExtra("buyer",tv_name.getText().toString());
                intent.putExtra("method",tv_method.getText().toString());
                startActivity(intent);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void initView(){
        btn_back=findViewById(R.id.btn_back);
        btn_scan=findViewById(R.id.btn_scan);
        btn_setup=findViewById(R.id.btn_setup);

        tv_name=findViewById(R.id.tv_name);
        tv_method=findViewById(R.id.tv_method);
    }
}