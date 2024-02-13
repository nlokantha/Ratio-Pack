package com.example.ratiopack.SolidPack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ratiopack.R;
import com.example.ratiopack.model.SolidProfile;

import java.util.ArrayList;
import java.util.List;

public class Solid_Scanning_Activity extends AppCompatActivity {
    private AppCompatButton btn_back,btn_next;
    private TextView tv_name,tv_method,tv_poNumber,tv_numberOfCarton,tv_pcNumber2,tv_pieces,tv_scanSize,tv_pieceNumber;
    private EditText edit_add;
    int i=0;

    List<SolidProfile> solidProfileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solid_scanning);
        initView();
        getAndSetIntent();
        solidProfileList = new ArrayList<>();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        edit_add.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_TAB && event.getAction() == KeyEvent.ACTION_DOWN){

                    String poNumber = tv_poNumber.getText().toString();
                    String cartonCount = tv_numberOfCarton.getText().toString();
                    String cartonNumber = getIntent().getStringExtra(Solid_UPC_Activity.SCAN_CARTON);
                    String UpcNumber=getIntent().getStringExtra(Solid_UPC_Activity.SCAN_UPC);
                    String maxPieceCount= tv_pieces.getText().toString();



                    tv_pieceNumber.setText(edit_add.getText().toString());
                    int quantity = Integer.parseInt(tv_pieces.getText().toString());
                    if (i<quantity){
                        tv_scanSize.setText(String.valueOf(i+1 ));
                        i++;
                        SolidProfile profile = new SolidProfile(poNumber,cartonCount,cartonNumber,UpcNumber,maxPieceCount,edit_add.getText().toString());
                        solidProfileList.add(profile);
                        Toast.makeText(Solid_Scanning_Activity.this, profile.toString(), Toast.LENGTH_SHORT).show();
                        edit_add.setText("");

                        if (i == quantity) {
                            btn_next.setVisibility(View.VISIBLE);
                            tv_pieceNumber.setText("Full Filled");
                            edit_add.setVisibility(View.GONE);
                        }
                    }else{
//                        Toast.makeText(Solid_Scanning_Activity.this, "Nice.........", Toast.LENGTH_SHORT).show();
                        edit_add.setText("");
//                        tv_pieceNumber.setText("N/A");
                    }
                    return true;
                }

                return false;
            }
        });

        edit_add.requestFocus();


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void getAndSetIntent() {
        if (getIntent() != null){
            String buyer=getIntent().getStringExtra(Solid_UPC_Activity.BUYER);
            String method=getIntent().getStringExtra(Solid_UPC_Activity.METHOD);
            String poNumber=getIntent().getStringExtra(Solid_UPC_Activity.PONUMBER);
            String numberOfCarton=getIntent().getStringExtra(Solid_UPC_Activity.CARTON_COUNT);
            String scanCarton = getIntent().getStringExtra(Solid_UPC_Activity.SCAN_CARTON);
            String scanUpc=getIntent().getStringExtra(Solid_UPC_Activity.SCAN_UPC);
            String maxPieces=getIntent().getStringExtra(Solid_UPC_Activity.MAX_PIECES);

            tv_name.setText(buyer);
            tv_method.setText(method);
            tv_poNumber.setText(poNumber);
            tv_numberOfCarton.setText(numberOfCarton);
            tv_pcNumber2.setText(scanUpc);
            tv_pieces.setText(maxPieces);



        }
    }

    private void initView() {
        btn_next=findViewById(R.id.btn_next);
        btn_back=findViewById(R.id.btn_back);

        tv_name = findViewById(R.id.tv_name);
        tv_method = findViewById(R.id.tv_method);
        tv_poNumber = findViewById(R.id.tv_poNumber);
        tv_numberOfCarton = findViewById(R.id.tv_numberOfCarton);
        tv_pcNumber2 = findViewById(R.id.tv_pcNumber2);
        tv_pieces = findViewById(R.id.tv_pieces);
        tv_scanSize = findViewById(R.id.tv_scanSize);
        tv_pieceNumber = findViewById(R.id.tv_pieceNumber);

        edit_add=findViewById(R.id.edit_add);
    }
}