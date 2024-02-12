package com.example.ratiopack.SolidPack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ratiopack.Controls.Constant;
import com.example.ratiopack.R;

public class Solid_UPC_Activity extends AppCompatActivity {
    private TextView tv_name,tv_method,tv_poNumber,tv_numberOfCarton;
    private AppCompatButton btn_next,btn_clear,btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solid_upc);
        initView();
        getAndSetIntent();


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initView() {
        tv_name = findViewById(R.id.tv_name);
        tv_method = findViewById(R.id.tv_method);
        tv_poNumber = findViewById(R.id.tv_poNumber);
        tv_numberOfCarton = findViewById(R.id.tv_numberOfCarton);

        btn_back=findViewById(R.id.btn_back);
        btn_next=findViewById(R.id.btn_next);
        btn_clear=findViewById(R.id.btn_clear);
    }

    private void getAndSetIntent() {
        if (getIntent()!=null && getIntent().hasExtra(Solid_PO_Activity.BUYER) && getIntent().hasExtra(Solid_PO_Activity.METHOD)
                && getIntent().hasExtra(Solid_PO_Activity.PONUMBER) && getIntent().hasExtra(Solid_PO_Activity.CARTON_COUNT)){

            String buyer=getIntent().getStringExtra(Solid_PO_Activity.BUYER);
            String method=getIntent().getStringExtra(Solid_PO_Activity.METHOD);
            String poNumber=getIntent().getStringExtra(Solid_PO_Activity.PONUMBER);
            String numberOfCarton=getIntent().getStringExtra(Solid_PO_Activity.CARTON_COUNT);

            tv_name.setText(buyer);
            tv_method.setText(method);
            tv_poNumber.setText(poNumber);
            tv_numberOfCarton.setText(numberOfCarton);
        }else {

        }
    }
}