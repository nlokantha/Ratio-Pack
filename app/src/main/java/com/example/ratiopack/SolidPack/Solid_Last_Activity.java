package com.example.ratiopack.SolidPack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ratiopack.Controls.Method;
import com.example.ratiopack.MainActivity;
import com.example.ratiopack.R;
import com.example.ratiopack.ScanActivity;
import com.example.ratiopack.model.Cons;
import com.example.ratiopack.model.Final;
import com.example.ratiopack.model.SolidProfile;
import com.opencsv.CSVWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class Solid_Last_Activity extends AppCompatActivity {
    private AppCompatButton btn_scan,btn_end;
    private TextView tv_name,tv_method,tv_poNumber,tv_numberOfCarton,tv_pcNumber2,tv_pieces,tv_scanSize,tv_pieceNumber;


    ArrayList<SolidProfile> solidProfileList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solid_last);
        initView();
        getandsetIntent();


        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Solid_Last_Activity.this, "Hi I am Namal", Toast.LENGTH_SHORT).show();
            }
        });

        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCSV();
                Method.alerts(Solid_Last_Activity.this,"Success","Solid packing file Created Successfully");
                Intent intent = new Intent(Solid_Last_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        btn_scan = findViewById(R.id.btn_scan);
        btn_end = findViewById(R.id.btn_end);

        tv_name=findViewById(R.id.tv_name);
        tv_method=findViewById(R.id.tv_method);
        tv_poNumber=findViewById(R.id.tv_poNumber);
        tv_numberOfCarton=findViewById(R.id.tv_numberOfCarton);
        tv_pcNumber2=findViewById(R.id.tv_pcNumber2);


    }

    private void getandsetIntent() {
        if (getIntent()!=null && getIntent().hasExtra(Solid_Scanning_Activity.SEND_CONS) && getIntent().hasExtra(Solid_Scanning_Activity.SEND_ARRAY) ){
            solidProfileList = (ArrayList<SolidProfile>) getIntent().getSerializableExtra(Solid_Scanning_Activity.SEND_ARRAY);
            Cons cons = (Cons) getIntent().getSerializableExtra(Solid_Scanning_Activity.SEND_CONS);

            if (cons != null){
                tv_name.setText(cons.getBuyer());
                tv_method.setText(cons.getMethod());
                tv_poNumber.setText(cons.getPoNumber());
                tv_numberOfCarton.setText(cons.getCartonCount());
                tv_pcNumber2.setText(cons.getPcNumber());
            }

        }
    }

    private void saveCSV(){
        try {
            File directory;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                directory = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
            } else {
                directory = new File(Environment.getExternalStorageDirectory(), "SolidPack");
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
            csvWriter.writeNext(new String[]{"PO Number", "Carton Count", "Carton Number", "UPC Number", "Carton Max Pieces","BarCode"});

            // Writing user data
            for (SolidProfile currentUser : solidProfileList) {
                String[] userData = {currentUser.getPoNumber(),currentUser.getCartonCount(),currentUser.getCartonNumber(),
                        currentUser.getUpcNumber(), currentUser.getMaxPieceCount(), currentUser.getBarCode()};
                csvWriter.writeNext(userData);
            }

            csvWriter.close();
//            Toast.makeText(Solid_Last_Activity.this, "CSV file saved successfully in Documents folder", Toast.LENGTH_SHORT).show();
//
        }catch (IOException e){
            e.printStackTrace();
        }
    }



}