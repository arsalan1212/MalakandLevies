package com.example.arsalankhan.malakandlevies.Dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by Arsalan khan on 2/10/2017.
 */

public class alertDialog {
    public static AlertDialog alertDialog;


    public static void setAlertDialog(Context context){
       alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setCancelable(false);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });
    }

    public static void setTitle(String title){
        alertDialog.setTitle(title);
    }

    public static void setMessage(String message){
        alertDialog.setMessage(message);
    }
    public static void show(){
        alertDialog.show();
    }
}
