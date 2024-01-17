package com.example.ratiopack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    public static final String TAG="demo";
    TextInputLayout textInputLayoutBuyer,textInputLayoutMethod;
    MaterialAutoCompleteTextView tv_buyer,tv_method;
    AppCompatButton btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_buyer.getText().toString().isEmpty()){
                    textInputLayoutBuyer.setError("Please Select a Buyer ");
                } else if (tv_method.getText().toString().isEmpty()) {
                    textInputLayoutMethod.setError("Please Select a Method");
                }else {
                    if(tv_method.getText().toString().equals("Ratio Pack")){
                        Intent intent= new Intent(MainActivity.this, MenuActivity.class);
                        intent.putExtra("buyer",tv_buyer.getText().toString());
                        intent.putExtra("method",tv_method.getText().toString());
                        startActivity(intent);
                    }
                }
            }
        });
    }
    public void initView(){
        textInputLayoutBuyer = findViewById(R.id.inputLayoutBuyer);
        textInputLayoutMethod = findViewById(R.id.inputLayoutMethod);

        tv_buyer=findViewById(R.id.inputBuyer);
        tv_method=findViewById(R.id.inputMethod);

        btn_next=findViewById(R.id.btn_next);
    }
}