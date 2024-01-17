package com.example.ratiopack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ratiopack.Controls.Constant;
import com.example.ratiopack.Controls.Method;

public class PoPcUpc extends AppCompatActivity {

    private EditText et_poNumber,et_numberofCarton;
    AppCompatButton btn_next,btn_back,btn_clear;
    private TextView tv_name,tv_method,tv_template;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_po_pc_upc);
        initView();

//      get/Set Intent Data
        if (getIntent()!=null && getIntent().hasExtra(Constant.PackingMethod) && getIntent().hasExtra(Constant.Buyer) && getIntent().hasExtra("template")){
            String buyer=getIntent().getStringExtra(Constant.Buyer);
            String method=getIntent().getStringExtra(Constant.PackingMethod);
            String template=getIntent().getStringExtra("template");
            tv_name.setText(buyer);
            tv_method.setText(method);
            tv_template.setText(template);
        }



        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_poNumber.setText("");
                et_numberofCarton.setText("");
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_poNumber.getText().toString().equals("")){
                    Method.alert(PoPcUpc.this,"Please Enter PO Number ");
                } else if (et_numberofCarton.getText().toString().equals("")) {
                    Method.alert(PoPcUpc.this,"Please Enter Number of Carton");
                } else {
                    Intent intent=new Intent(PoPcUpc.this, UpcAndCarton.class);
                    String buyer=tv_name.getText().toString();
                    String method=tv_method.getText().toString();
                    String template=tv_template.getText().toString();
                    String poNumber=et_poNumber.getText().toString();
                    String cartonNumber=et_numberofCarton.getText().toString();
                    intent.putExtra("buyer",buyer);
                    intent.putExtra("method",method);
                    intent.putExtra("template",template);
                    intent.putExtra("poNumber",poNumber);
                    intent.putExtra("cartonNumber",cartonNumber);
                    startActivity(intent);
                }
            }
        });

    }

    public void initView(){
//        EditText...........
        et_poNumber=findViewById(R.id.et_poNumber);
//        et_upcNumber=findViewById(R.id.et_upcNumber);
//        et_cartonNumber=findViewById(R.id.et_cartonNumber);
        et_numberofCarton=findViewById(R.id.et_numberofCarton);

//        Buttons...........

        btn_next=findViewById(R.id.btn_next);
        btn_back=findViewById(R.id.btn_back);
        btn_clear=findViewById(R.id.btn_clear);

//        TextView..............

        tv_name=findViewById(R.id.tv_name);
        tv_method=findViewById(R.id.tv_method);
        tv_template=findViewById(R.id.tv_template);

    }
}
