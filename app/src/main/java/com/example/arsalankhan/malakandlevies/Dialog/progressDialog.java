package com.example.arsalankhan.malakandlevies.Dialog;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Arsalan khan on 2/10/2017.
 */

public class progressDialog {
    public static ProgressDialog dialog;
    public static void setProgressDialog(Context context){
         dialog=new ProgressDialog(context);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
    }

    public static void setMessage(String message){
        dialog.setMessage(message);
    }

    public static void setTitle(String title){
        dialog.setTitle(title);
    }

    public static void show(){
        dialog.show();
    }
    public static void hide(){
        dialog.dismiss();
    }
}
