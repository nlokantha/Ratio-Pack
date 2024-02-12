package com.example.ratiopack;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ratiopack.Controls.Method;
import com.example.ratiopack.RoomDatabase.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SetupActivity extends AppCompatActivity {
    public static final String TAG ="demo";
    private String[] dataArray, dataColor;
    private int currentIndex = 0;
    private int currentColor = 0;
    private int qua = 0;

    private AppCompatButton btn_nextColor, btn_nextSize, btn_add, btn_remove, btn_finish;
    private TextView text_color, text_size, text_quantity, tv_name, tv_method;
    private EditText et_barCode;
    private StringBuilder scannedBarcode = new StringBuilder();

    List<User> userList;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        initView();
        // Set/Get Intent DATA
        if (getIntent() != null && getIntent().hasExtra("buyer") && getIntent().hasExtra("method")) {
            tv_name.setText(getIntent().getStringExtra("buyer"));
            tv_method.setText(getIntent().getStringExtra("method"));
        }

        btn_nextSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Put data to Array
                String color = text_color.getText().toString();
                String size = text_size.getText().toString();
                String quantity = text_quantity.getText().toString();
                String barCode = et_barCode.getText().toString();

                user = new User(color, size, quantity, barCode);
                userList.add(user);
                et_barCode.setText("");

                currentIndex = (currentIndex + 1) % dataArray.length;
                text_size.setText(dataArray[currentIndex]);

                // Set quantity back to 0
                qua =0;
                text_quantity.setText("" + qua);
//                Toast.makeText(SetupActivity.this, "Add Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qua++;
                text_quantity.setText("" + qua);
            }
        });

        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qua <= 0) {
                    qua = 0;
                } else {
                    qua--;
                    text_quantity.setText("" + qua);
                }
            }
        });

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(SetupActivity.this);
                builder.setTitle("Alert")
                        .setMessage("Do you Want to Save?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(SetupActivity.this, ScanUpcActivity.class);
                                intent.putExtra("profile",(Serializable) userList);
                                intent.putExtra("buyer",tv_name.getText().toString());
                                intent.putExtra("method",tv_method.getText().toString());
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.create().show();

            }
        });

        btn_nextColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentColor = (currentColor + 1) % dataColor.length;
                text_color.setText(dataColor[currentColor]);

                currentIndex = 0;
                text_size.setText(dataArray[currentIndex]);
            }
        });
    }
    public void initView() {
        btn_nextColor = findViewById(R.id.btn_nextColor);
        btn_nextSize = findViewById(R.id.btn_nextSize);
        btn_add = findViewById(R.id.btn_add);
        btn_remove = findViewById(R.id.btn_remove);
        btn_finish = findViewById(R.id.btn_finish);

        text_color = findViewById(R.id.text_color);
        text_size = findViewById(R.id.text_size);
        text_quantity = findViewById(R.id.text_quantity);
        tv_name = findViewById(R.id.tv_name);
        tv_method = findViewById(R.id.tv_method);

        et_barCode = findViewById(R.id.et_barCode);

        dataArray = getResources().getStringArray(R.array.carton_size);
        dataColor = getResources().getStringArray(R.array.color);
        text_size.setText(dataArray[currentIndex]);
        text_color.setText(dataColor[currentColor]);

        userList = new ArrayList<>();
    }

}
