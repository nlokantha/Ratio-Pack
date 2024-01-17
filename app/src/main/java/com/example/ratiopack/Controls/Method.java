package com.example.ratiopack.Controls;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class Method {
    public static void alert(Context context ,String mes){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Alert")
                .setMessage(mes)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.create().show();
    }
}
