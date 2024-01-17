package com.example.ratiopack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ratiopack.Controls.Constant;
import com.example.ratiopack.RoomDatabase.Upc;
import com.example.ratiopack.RoomDatabase.User;
import com.example.ratiopack.RoomDatabase.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class ScanUpcActivity extends AppCompatActivity {

    public static final String TAG ="demo";

    TextView tv_name,tv_method;
    UserDatabase database;
    EditText et_upc;
    Button btn_f;
    List<User> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_upc);
        et_upc=findViewById(R.id.et_upc);
        btn_f=findViewById(R.id.btn_f);
        tv_name=findViewById(R.id.tv_name);
        tv_method=findViewById(R.id.tv_method);
        database = UserDatabase.getInstance(this);

        if (getIntent() != null && getIntent().hasExtra("buyer") && getIntent().hasExtra("method")){
            tv_name.setText(getIntent().getStringExtra("buyer"));
            tv_method.setText(getIntent().getStringExtra("method"));
        }

//        ...................
        if (getIntent() != null && getIntent().hasExtra("profile")){
            Intent intent = getIntent();
            userList = (ArrayList<User>) intent.getSerializableExtra("profile");
        }

        btn_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String upcNumber = et_upc.getText().toString().trim();
                Upc upc = new Upc(upcNumber);
                database.upcDao().insert(upc);

                for (User user : userList) {
                    user.setUpcNumber(upcNumber);
                    database.userDao().insert(user);
                }
                userList.clear();
                Toast.makeText(ScanUpcActivity.this, "Data added to the database", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ScanUpcActivity.this, MenuActivity.class);
                String buyer=tv_name.getText().toString();
                String method=tv_method.getText().toString();
                intent.putExtra("b",buyer);
                intent.putExtra("m",method);
                startActivity(intent);

            }
        });



    }
}