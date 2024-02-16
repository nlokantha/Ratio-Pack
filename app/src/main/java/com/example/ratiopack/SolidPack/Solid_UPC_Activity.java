package com.example.ratiopack.SolidPack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ratiopack.Controls.Constant;
import com.example.ratiopack.Controls.Method;
import com.example.ratiopack.R;
import com.example.ratiopack.model.Cons;

public class Solid_UPC_Activity extends AppCompatActivity {
    private TextView tv_name,tv_method,tv_poNumber,tv_numberOfCarton;
    private AppCompatButton btn_next,btn_clear,btn_back;
    private EditText edit_scanCarton,edit_scanUpc,edit_maxPieces;

    public static final String BUYER = "buyer";
    public static final String METHOD = "method";
    public static final String PONUMBER = "ponumber";
    public static final String CARTON_COUNT = "count";
    public static final String SCAN_CARTON = "scan carton";
    public static final String SCAN_UPC = "scan upc";
    public static final String MAX_PIECES = "maxpieces";

    int z=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solid_upc);
        initView();
        getAndSetIntent();
        cartoncountIntent();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_scanCarton.setText("");
                edit_scanUpc.setText("");
                edit_maxPieces.setText("");

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buyer=tv_name.getText().toString();
                String method=tv_method.getText().toString();
                String poNumber=tv_poNumber.getText().toString();
                String numberOfCarton=tv_numberOfCarton.getText().toString();

                String scanCarton = edit_scanCarton.getText().toString();
                String scanUpc = edit_scanUpc.getText().toString();
                String maxPieces= edit_maxPieces.getText().toString();


                if (scanCarton.isEmpty()){
                    Method.alert(Solid_UPC_Activity.this,"Please Scan the Carton Number");
                } else if (scanUpc.isEmpty()) {
                    Method.alert(Solid_UPC_Activity.this,"Please Scan the UPC Number");
                } else if (maxPieces.isEmpty()) {
                    Method.alert(Solid_UPC_Activity.this,"Please Enter the Valid Pice Count");
                }else {
                    Intent intent=new Intent(Solid_UPC_Activity.this, Solid_Scanning_Activity.class);
                    intent.putExtra(BUYER,buyer);
                    intent.putExtra(METHOD,method);
                    intent.putExtra(PONUMBER,poNumber);
                    intent.putExtra(CARTON_COUNT,numberOfCarton);
                    intent.putExtra(SCAN_CARTON,scanCarton);
                    intent.putExtra(SCAN_UPC,scanUpc);
                    intent.putExtra(MAX_PIECES,maxPieces);
                    intent.putExtra("CountZ",z);
                    startActivity(intent);

                }

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

        edit_scanCarton=findViewById(R.id.edit_scanCarton);
        edit_scanUpc=findViewById(R.id.edit_scanUpc);
        edit_maxPieces=findViewById(R.id.edit_maxPieces);
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

    private void cartoncountIntent(){
        if (getIntent() != null && getIntent().hasExtra("CountZ") && getIntent().hasExtra("conProfile")){
            z=getIntent().getIntExtra("CountZ",0);
            Cons cons = (Cons) getIntent().getSerializableExtra("conProfile");
            if (cons != null){
                tv_name.setText(cons.getBuyer());
                tv_method.setText(cons.getMethod());
                tv_poNumber.setText(cons.getPoNumber());
                tv_numberOfCarton.setText(cons.getCartonCount());
            }
        }
    }
}