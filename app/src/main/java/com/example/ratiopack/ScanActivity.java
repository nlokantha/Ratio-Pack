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
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ratiopack.RoomDatabase.User;
import com.example.ratiopack.RoomDatabase.UserDatabase;
import com.example.ratiopack.model.Final;
import com.opencsv.CSVWriter;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ScanActivity extends AppCompatActivity {

    TextView tv_color2,tv_size2,tv_upcnumber2,
            tv_quantity2,tv_scanQuantity,tv_name,tv_method,tv_template,
            tv_numberOfCarton,tv_cartonNumber,tv_poNumber,tv_pcNumber,tv_quSize,tv_cartonZ,tv_scanedCarton;
    AppCompatButton btn_next,btn_back,button,button2;
    List<User> userList;
    EditText edit_add;
    UserDatabase database;
    private int currentIndex=0;
    int inputCounter=1;
    List<Final> userfinal;
    int i=0;
    int z=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        initView();
        database = UserDatabase.getInstance(this);
        userfinal=new ArrayList<>();
        handsetIntent();
        displayUserData();

        if (getIntent()!=null && getIntent().hasExtra("CountZ")){
            z=getIntent().getIntExtra("CountZ",0);
            tv_cartonZ.setText(String.valueOf(z));
        }



        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextUserData();
                i=0;
            }
        });

//        btn_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String poNumber=tv_poNumber.getText().toString();
//                int cartonNumber=Integer.parseInt(tv_cartonNumber.getText().toString());
//                int pcNumber=Integer.parseInt(tv_pcNumber.getText().toString());
//                String color=tv_color2.getText().toString();
//                String size=tv_size2.getText().toString();
//                int quantity= Integer.parseInt(tv_quantity2.getText().toString());
//                int barcode= Integer.parseInt(edit_add.getText().toString());
//                if (i<quantity){
//                    tv_scanQuantity.setText(String.valueOf(i + 1));
//                    i++;
//                    Final fu=new Final(poNumber,cartonNumber,pcNumber,color,size,quantity,barcode);
//                    userfinal.add(fu);
//
//                }else {
//                    Toast.makeText(ScanActivity.this, "Please Select Next Color", Toast.LENGTH_SHORT).show();
//
//                }
//
//                System.out.println(userfinal.toString());
//            }
//        });
//        .....................24/1/17...................................
        edit_add.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_TAB && event.getAction() == KeyEvent.ACTION_DOWN){
                    String poNumber=tv_poNumber.getText().toString();
                    int cartonNumber=Integer.parseInt(tv_cartonNumber.getText().toString());
                    int pcNumber=Integer.parseInt(tv_pcNumber.getText().toString());
                    String color=tv_color2.getText().toString();
                    String size=tv_size2.getText().toString();
                    int quantity= Integer.parseInt(tv_quantity2.getText().toString());
                    long barcode= Long.parseLong(edit_add.getText().toString());
                    if (i<quantity){
                        tv_scanQuantity.setText(String.valueOf(i + 1));
                        i++;
                        Final fu=new Final(poNumber,cartonNumber,pcNumber,color,size,quantity,barcode);
                        userfinal.add(fu);
                        edit_add.setText("");


                    }else {
                        Toast.makeText(ScanActivity.this, "Please Select Next Color", Toast.LENGTH_SHORT).show();
                        edit_add.setText("");

                    }
                }
                return false;
            }
        });

        edit_add.requestFocus();


//        .........................................
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkStoragePermission()) {
//                    saveCSVFile();
                    AlertDialog.Builder builder=new AlertDialog.Builder(ScanActivity.this);
                    builder.setTitle("Alert")
                            .setMessage("Do you Want to Save")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    saveCSV();
                                    Intent intent=new Intent(ScanActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    builder.create().show();
                } else {
                    requestStoragePermission();
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkStoragePermission()) {
                    z++;
                    Intent intent=new Intent(ScanActivity.this, UpcAndCarton.class);
                    intent.putExtra("buyer",tv_name.getText().toString());
                    intent.putExtra("method",tv_method.getText().toString());
                    intent.putExtra("template",tv_template.getText().toString());
                    intent.putExtra("poNumber",tv_poNumber.getText().toString());
                    intent.putExtra("cartonNumber",tv_numberOfCarton.getText().toString());
                    intent.putExtra("CountZ",z);
                    startActivity(intent);
                    saveCSV();
//                    saveCSVFile();
                } else {
                    requestStoragePermission();
                }
            }
        });
    }
    private void handsetIntent() {
        if (getIntent()!=null && getIntent().hasExtra("buyer")&& getIntent().hasExtra("method")
                && getIntent().hasExtra("template") && getIntent().hasExtra("poNumber")
                && getIntent().hasExtra("numberOfCarton")&& getIntent().hasExtra("cartonNumber")
                && getIntent().hasExtra("upcNumber")){


            tv_name.setText(getIntent().getStringExtra("buyer"));
            tv_method.setText(getIntent().getStringExtra("method"));
            tv_template.setText(getIntent().getStringExtra("template"));
            tv_poNumber.setText(getIntent().getStringExtra("poNumber"));
            tv_numberOfCarton.setText(getIntent().getStringExtra("numberOfCarton"));

            tv_scanedCarton.setText(getIntent().getStringExtra("numberOfCarton"));

            tv_cartonNumber.setText(getIntent().getStringExtra("cartonNumber"));
            tv_pcNumber.setText(getIntent().getStringExtra("upcNumber"));
            String template=getIntent().getStringExtra("template");
            userList= database.userDao().getbyUpc(template);
        }
    }

    private void displayUserData() {
        if (currentIndex >= 0 && currentIndex < userList.size()) {
            User currentUser = userList.get(currentIndex);
            tv_quantity2.setText(currentUser.getQuantity());
            tv_quSize.setText(currentUser.getQuantity());
            tv_size2.setText(currentUser.getSize());
            tv_color2.setText(currentUser.getColor());
        }
    }

    public void initView(){

        tv_quantity2=findViewById(R.id.tv_quantity2);
        tv_size2=findViewById(R.id.tv_size2);
        tv_color2=findViewById(R.id.tv_color2);
        tv_scanQuantity=findViewById(R.id.tv_scanQuantity);
        tv_name=findViewById(R.id.tv_name);
        tv_method=findViewById(R.id.tv_method);
        tv_template=findViewById(R.id.tv_template);
        tv_numberOfCarton=findViewById(R.id.tv_numberOfCarton);
        tv_cartonNumber=findViewById(R.id.tv_cartonNumber);
        tv_poNumber=findViewById(R.id.tv_poNumber);
        tv_pcNumber=findViewById(R.id.tv_pcNumber);
        tv_quSize=findViewById(R.id.tv_quSize);
        tv_cartonZ=findViewById(R.id.tv_cartonZ);
        tv_scanedCarton=findViewById(R.id.tv_scanedCarton);


        btn_next=findViewById(R.id.btn_next);
        edit_add=findViewById(R.id.edit_add);
//        btn_add=findViewById(R.id.btn_add);
        button=findViewById(R.id.button);
        button2=findViewById(R.id.button2);
        btn_back=findViewById(R.id.btn_back);

    }
    private void showNextUserData() {
        if (tv_scanQuantity.getText().toString().equals(tv_quSize.getText().toString())){
            if (currentIndex < userList.size() - 1) {
                currentIndex++;
                displayUserData();
                tv_scanQuantity.setText("0");}
            else {
                Toast.makeText(ScanActivity.this, "End of the list", Toast.LENGTH_SHORT).show();

//            Carton Scan ......................... 24/1/17.....................................
                int x=Integer.parseInt(tv_numberOfCarton.getText().toString());
                if (x>1 && (z+1)<x){
                    button2.setVisibility(View.VISIBLE);
                }
                button.setVisibility(View.VISIBLE);
            }
        }
    }

    private boolean checkStoragePermission() {
        int result = ContextCompat.checkSelfPermission(ScanActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }
    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(ScanActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                saveCSVFile();
                saveCSV();

            } else {
                Toast.makeText(ScanActivity.this, "Permission denied. Cannot save CSV file.", Toast.LENGTH_SHORT).show();
            }
        }
    }
//    private void saveCSVFile() {
//        try {
//            // Check if external storage is available
//            if (isExternalStorageWritable()) {
//                // Get the directory for the user's public pictures directory.
//                File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
//
//                // Create the directory if it doesn't exist
//                if (!directory.exists()) {
//                    directory.mkdirs();
//                }
//
//                String poNumber = tv_poNumber.getText().toString();
//                String fileName = poNumber + ".csv";
//
//                File file = new File(directory,fileName);
//                Writer writer = new OutputStreamWriter(new FileOutputStream(file,true));
//                CSVWriter csvWriter = new CSVWriter(writer);
//
//                // Writing header
//                csvWriter.writeNext(new String[]{"PO Number", "Carton Number", "PC Number", "Color", "Size", "Quantity", "BarCode"});
//
//                // Writing user data
//                for (Final currentUser : userfinal) {
//                    String[] userData = {currentUser.getPoNumber(), String.valueOf(currentUser.getCartonNumber()), String.valueOf(currentUser.getPcNumber()),
//                            currentUser.getColor(), currentUser.getSize(), String.valueOf(currentUser.getQuantity()), String.valueOf(currentUser.getBarcode())};
//                    csvWriter.writeNext(userData);
//                }
//
//                csvWriter.close();
//                Toast.makeText(ScanActivity.this, "CSV file saved successfully in Documents folder", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(ScanActivity.this, "External storage not available. Cannot save CSV file.", Toast.LENGTH_SHORT).show();
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    private boolean isExternalStorageWritable() {
//        String state = Environment.getExternalStorageState();
//        return Environment.MEDIA_MOUNTED.equals(state);
//    }
private void saveCSV(){
    try {
        File directory;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            directory = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        } else {
            directory = new File(Environment.getExternalStorageDirectory(), "Documents");
        }

        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }
        String poNumber = tv_poNumber.getText().toString();
        String fileName = poNumber + ".csv";

        File file = new File(directory,fileName);
//        Writer writer = new OutputStreamWriter(new FileOutputStream(file,true),"UTF-8");
//        Writer writer = new OutputStreamWriter(new FileOutputStream(file,true),"UTF-8");
        Writer writer = new BufferedWriter(new FileWriter(file, true));
        CSVWriter csvWriter = new CSVWriter(writer);

        // Writing header
        csvWriter.writeNext(new String[]{"PO Number", "Carton Number", "PC Number", "Color", "Size", "Quantity", "BarCode"});

        // Writing user data
        for (Final currentUser : userfinal) {
            String[] userData = {currentUser.getPoNumber(), String.valueOf(currentUser.getCartonNumber()), String.valueOf(currentUser.getPcNumber()),
                    currentUser.getColor(), currentUser.getSize(), String.valueOf(currentUser.getQuantity()), String.valueOf(currentUser.getBarcode())};
            csvWriter.writeNext(userData);
        }

        csvWriter.close();
        Toast.makeText(ScanActivity.this, "CSV file saved successfully in Documents folder", Toast.LENGTH_SHORT).show();
    }catch (IOException e){
        e.printStackTrace();
    }
}
}