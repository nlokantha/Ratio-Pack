package com.example.ratiopack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ratiopack.SolidPack.Solid_PO_Activity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    public static final String TAG="demo";
    TextInputLayout textInputLayoutBuyer,textInputLayoutMethod;
    MaterialAutoCompleteTextView tv_buyer,tv_method;
    AppCompatButton btn_next;
    public static final int REQUEST_CODE_WRITE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        requestStoragePermission();
//        2024-02-15.............

        if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.BLUETOOTH_CONNECT)){

                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage("We Need The Permission For.....")
                            .setCancelable(true)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_WRITE);
                                }
                            }).show();

            }else {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_WRITE);
            }

        }

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
                    else if(tv_method.getText().toString().equals("Solid Pack")){
                        Intent intent= new Intent(MainActivity.this, Solid_PO_Activity.class);
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
    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_WRITE){
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

            }else {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.BLUETOOTH_CONNECT)){
//                DENIED PERMISSION....
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage("You have Permanently denied this permission.,Go to Settings to Enable it.....")
                            .setPositiveButton("Go to Settings", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    gotoApplicationSettings();
                                }
                            })
                            .setNegativeButton("Cancle",null)
                            .setCancelable(false)
                            .show();
                }else {
                }
            }
        }
    }

    private void gotoApplicationSettings(){
        Intent intent=new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri=Uri.fromParts("package",this.getPackageName(),null);
        intent.setData(uri);
        startActivity(intent);
    }
}