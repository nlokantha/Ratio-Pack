package com.example.ratiopack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ratiopack.Controls.Method;

public class UpcAndCarton extends AppCompatActivity {
    private TextView tv_name,tv_method,tv_template,tv_po,tv_numberOfC;
    private EditText et_cartonNumber,et_upcNumber;
    private AppCompatButton btn_back,btn_next,btn_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upc_and_carton);
        initView();
        handsetIntent();
//        .................24/1/17....................
        et_cartonNumber.requestFocus();
//        ............................................
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_cartonNumber.setText("");
                et_upcNumber.setText("");
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_cartonNumber.getText().toString().equals("")){
                    Method.alert(UpcAndCarton.this,"Please Scan Carton Number");

                } else if (et_upcNumber.getText().toString().equals("")) {
                    Method.alert(UpcAndCarton.this,"Please Scan UPC Number");
                }else {
                    Intent intent=new Intent(UpcAndCarton.this, ScanActivity.class);
                    String buyer=tv_name.getText().toString();
                    String method=tv_method.getText().toString();
                    String template=tv_template.getText().toString();
                    String poNumber=tv_po.getText().toString();
                    String numberOfCarton=tv_numberOfC.getText().toString();
                    String cartonNumber=et_cartonNumber.getText().toString();
                    String upcNumber=et_upcNumber.getText().toString();

                    intent.putExtra("buyer",buyer);
                    intent.putExtra("method",method);
                    intent.putExtra("template",template);
                    intent.putExtra("poNumber",poNumber);
                    intent.putExtra("numberOfCarton",numberOfCarton);
                    intent.putExtra("cartonNumber",cartonNumber);
                    intent.putExtra("upcNumber",upcNumber);
                    startActivity(intent);
                }
            }
        });

        }

    private void handsetIntent() {
        if (getIntent()!=null && getIntent().hasExtra("buyer") && getIntent().hasExtra("method")
                && getIntent().hasExtra("template")&& getIntent().hasExtra("poNumber")&& getIntent().hasExtra("cartonNumber")) {

            tv_name.setText(getIntent().getStringExtra("buyer"));
            tv_method.setText(getIntent().getStringExtra("method"));
            tv_template.setText(getIntent().getStringExtra("template"));
            tv_po.setText(getIntent().getStringExtra("poNumber"));
            tv_numberOfC.setText(getIntent().getStringExtra("cartonNumber"));
        }
    }

    private void initView() {
        tv_name=findViewById(R.id.tv_name);
        tv_method=findViewById(R.id.tv_method);
        tv_template=findViewById(R.id.tv_template);
        tv_po=findViewById(R.id.tv_po);
        tv_numberOfC=findViewById(R.id.tv_numberOfC);

        et_cartonNumber=findViewById(R.id.et_cartonNumber);
        et_upcNumber=findViewById(R.id.et_upcNumber);

        btn_back=findViewById(R.id.btn_back);
        btn_next=findViewById(R.id.btn_next);
        btn_clear=findViewById(R.id.btn_clear);
    }

}