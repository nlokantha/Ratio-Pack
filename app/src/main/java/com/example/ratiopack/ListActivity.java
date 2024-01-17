package com.example.ratiopack;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ratiopack.Controls.Constant;
import com.example.ratiopack.Controls.Method;
import com.example.ratiopack.RoomDatabase.Upc;
import com.example.ratiopack.RoomDatabase.User;
import com.example.ratiopack.RoomDatabase.UserDatabase;

import java.util.List;

public class ListActivity extends AppCompatActivity {
     public static final String TAG = "demo";
     UserDatabase database;
     private AppCompatButton btn_back,btn_next;
     private TextView tv_name,tv_method;
     private EditText et_selectUpc;
    RecyclerView recyclerView;
    UserAdapter userAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initView();
        List<Upc> upcList = database.upcDao().getAll();

//        get and set Intent Data --

        if (getIntent() != null && getIntent().hasExtra("buyer") && getIntent().hasExtra("method")){
            tv_name.setText(getIntent().getStringExtra("buyer"));
            tv_method.setText(getIntent().getStringExtra("method"));
        }

        if (getIntent() != null && getIntent().hasExtra(Constant.Buyer) && getIntent().hasExtra(Constant.PackingMethod)){
            tv_name.setText(getIntent().getStringExtra(Constant.Buyer));
            tv_method.setText(getIntent().getStringExtra(Constant.PackingMethod));
        }
//        ....................


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        et_selectUpc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUPCDialog(upcList);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_selectUpc.getText().toString().equals("")){
                    Method.alert(ListActivity.this,"Please Select the Template");
                }else {
                    Intent intent=new Intent(ListActivity.this, PoPcUpc.class);
                    String buyerName=tv_name.getText().toString();
                    String method=tv_method.getText().toString();
                    String template=et_selectUpc.getText().toString();
                    intent.putExtra(Constant.Buyer,buyerName);
                    intent.putExtra(Constant.PackingMethod,method);
                    intent.putExtra("template",template);
                    startActivity(intent);
                }
            }
        });
    }
    public void initView(){
        btn_back=findViewById(R.id.btn_back);
        tv_name=findViewById(R.id.tv_name);
        tv_method=findViewById(R.id.tv_method);
        et_selectUpc=findViewById(R.id.et_selectUpc);
        recyclerView=findViewById(R.id.recyclerView);
        btn_next=findViewById(R.id.btn_next);
        database = UserDatabase.getInstance(this);
    }
    private void showUPCDialog(List<Upc> upcList) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select UPC Number");

        ArrayAdapter<String> dialogAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (Upc upc : upcList) {
            dialogAdapter.add(upc.getUpcNumber());
        }
        builder.setAdapter(dialogAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String selectedUpc = upcList.get(which).getUpcNumber();
                updateListViews(selectedUpc);
                et_selectUpc.setText(selectedUpc);
            }
        });
        builder.show();
    }
    private void updateListViews(String selectedUpc) {
        List<User> userList = database.userDao().getbyUpc(selectedUpc);
        userAdapter = new UserAdapter(userList);
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}