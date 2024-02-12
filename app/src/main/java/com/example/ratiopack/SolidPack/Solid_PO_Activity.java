package com.example.ratiopack.SolidPack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ratiopack.Controls.Method;
import com.example.ratiopack.PoPcUpc;
import com.example.ratiopack.R;

public class Solid_PO_Activity extends AppCompatActivity {

    private TextView tv_name,tv_method;
    private AppCompatButton btn_back,btn_next,btn_clear;
    private EditText edit_poNumber,edit_numberOfCarton;

    public static final String BUYER = "buyer";
    public static final String METHOD = "method";
    public static final String PONUMBER = "ponumber";
    public static final String CARTON_COUNT = "count";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solid_po);
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
                edit_poNumber.setText("");
                edit_numberOfCarton.setText("");
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buyer=tv_name.getText().toString();
                String method=tv_method.getText().toString();
                String poNumber=edit_poNumber.getText().toString();
                String numberOfCarton=edit_numberOfCarton.getText().toString();

                if (poNumber.isEmpty()){
                    Method.alert(Solid_PO_Activity.this,"Please Enter a Valid Purchase Order Number ");
                } else if (numberOfCarton.isEmpty()) {
                    Method.alert(Solid_PO_Activity.this,"Please Enter a Valid Carton Count");
                }else {
//                    Toast.makeText(Solid_PO_Activity.this, "You Click Me", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Solid_PO_Activity.this, Solid_UPC_Activity.class);
                    intent.putExtra(BUYER,buyer);
                    intent.putExtra(METHOD,method);
                    intent.putExtra(PONUMBER,poNumber);
                    intent.putExtra(CARTON_COUNT,numberOfCarton);
                    startActivity(intent);

                }
            }
        });




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

        btn_next=findViewById(R.id.btn_next);
        btn_back=findViewById(R.id.btn_back);
        btn_clear=findViewById(R.id.btn_clear);

        edit_poNumber=findViewById(R.id.edit_poNumber);
        edit_numberOfCarton=findViewById(R.id.edit_numberOfCarton);
    }
}