package com.example.arsalankhan.malakandlevies;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.arsalankhan.malakandlevies.Dialog.alertDialog;
import com.example.arsalankhan.malakandlevies.helper.CrimeModel;
import com.example.arsalankhan.malakandlevies.helper.CriminalModel;
import com.example.arsalankhan.malakandlevies.helper.POModel;
import com.example.arsalankhan.malakandlevies.helper.forceModel;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SplashScreen extends AppCompatActivity {

    RelativeLayout layout_splash_screen;
    LinearLayout layout_internet_error;
    AVLoadingIndicatorView avLoadingIndicatorView;
    TextView tv_internet_connection;
    ArrayList<CriminalModel> arrayList_criminal=new ArrayList<>();
    ArrayList<POModel> arrayList_po=new ArrayList<>();
    ArrayList<CrimeModel> arrayList_crime=new ArrayList<>();
    ArrayList<forceModel> arrayList_force=new ArrayList<>();

    private final static String url_criminal_po=AppUrl.url_criminal_po;
    private final static String url_force=AppUrl.url_force;
    private final static String url_crime=AppUrl.url_crime;

    boolean isCounterFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        isCounterFinish=false;
        initViews();


        setRequest(url_criminal_po);
        setRequest(url_force);
        setRequest(url_crime);

        new CountDownTimer(5000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                 avLoadingIndicatorView.show();
            }

            @Override
            public void onFinish() {
                isCounterFinish=true;
                CheckArrayList();
                    Log.d("TAG","lOADER COMPLETE");
            }
        }.start();



    }

    private void initViews() {
        layout_splash_screen= (RelativeLayout) findViewById(R.id.activity_splash_screen);
        layout_internet_error= (LinearLayout) findViewById(R.id.internet_error);
        avLoadingIndicatorView= (AVLoadingIndicatorView) findViewById(R.id.avi);
        tv_internet_connection= (TextView) findViewById(R.id.tv_internet_error);

        layout_splash_screen.setVisibility(View.VISIBLE);
        layout_internet_error.setVisibility(View.GONE);
        avLoadingIndicatorView.show();
    }

    private void setRequest(final String url) {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response!=null){
                    layout_internet_error.setVisibility(View.GONE);
                    layout_splash_screen.setVisibility(View.VISIBLE);

                    if(url.equals(url_criminal_po)){
                        JsonParserCriminalPO(response);
                    }
                    else if(url.equals(url_force)){
                        JsonParserForce(response);
                    }
                    else if(url.equals(url_crime)){
                        JsonParserCrime(response);
                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                layout_internet_error.setVisibility(View.VISIBLE);
                layout_splash_screen.setVisibility(View.GONE);

                if(error instanceof NetworkError){
                    tv_internet_connection.setText("Cannot connect to Internet...Please check your connection!");
                }
                else if(error instanceof NoConnectionError){
                    tv_internet_connection.setText("Cannot connect to Internet...Please check your connection!");
                }
                else if(error instanceof TimeoutError){
                    tv_internet_connection.setText("Connection TimeOut! Please check your internet connection!");
                }
                else if (error instanceof ServerError) {
                    tv_internet_connection.setText("The server could not be found. Please try again after some time!!");
                } else if (error instanceof AuthFailureError) {
                    tv_internet_connection.setText("Cannot connect to Internet...Please check your connection!");
                } else if (error instanceof ParseError) {
                    tv_internet_connection.setText("Parsing error! Please try again after some time!!");
                }
            }
        });

        MySingleton.getInstance(SplashScreen.this).addToRequestQueue(stringRequest);
    }


    // Json Parser for CRIME
    private void JsonParserCrime(String response) {

        try {
            Gson gson=new Gson();
            JSONArray parentArray=new JSONArray(response);
            for (int i = 0; i <parentArray.length() ; i++) {
                JSONObject jsonObject=parentArray.getJSONObject(i);

                        CrimeModel crimeModel=gson.fromJson(jsonObject.toString(),CrimeModel.class);
                        arrayList_crime.add(crimeModel);


            }
            CheckArrayList();
            Log.d("TAG","cRIME cOmplete");


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    // force JSON Parser
    private void JsonParserForce(String response) {

        try {
            JSONArray parentArray=new JSONArray(response);
            for (int i = 0; i <parentArray.length() ; i++) {
                JSONObject jsonObject=parentArray.getJSONObject(i);


                if(jsonObject.getString("code").equals("success")){

                    forceModel ForceModel=new forceModel();
                    ForceModel.setForceName(jsonObject.getString("force_name"));
                    ForceModel.setForceRank(jsonObject.getString("force_rank"));
                    ForceModel.setForceRegNumber(jsonObject.getString("force_reg_no"));
                    arrayList_force.add(ForceModel);
                }else{
                    alertDialog.setAlertDialog(SplashScreen.this);
                    alertDialog.setTitle("Server Response!");
                    alertDialog.setMessage("No, Data in Database");
                    alertDialog.show();
                }

            }
            CheckArrayList();
            Log.d("TAG","Force COMPLETE");


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    // CRIMINAL And PO Parser
    private void JsonParserCriminalPO(String response) {

        try {
            Gson gson=new Gson();
            JSONArray parentArray=new JSONArray(response);
            for (int i = 0; i <parentArray.length() ; i++) {
                JSONObject jsonObject=parentArray.getJSONObject(i);


                if(jsonObject.getString("code").equals("success")){

                    if(jsonObject.getString("status").equals("Criminal")){
                        CriminalModel criminalModel=gson.fromJson(jsonObject.toString(),CriminalModel.class);
                        arrayList_criminal.add(criminalModel);
                    }
                    else if(jsonObject.getString("status").equals("P.O")){
                        POModel poModel=gson.fromJson(jsonObject.toString(),POModel.class);
                        arrayList_po.add(poModel);
                    }

                }else{
                    alertDialog.setAlertDialog(SplashScreen.this);
                    alertDialog.setTitle("Server Response!");
                    alertDialog.setMessage("No, Data in Database");
                    alertDialog.show();
                }

            }
            CheckArrayList();
            Log.d("TAG","Criminal COMPLETE");



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void Refresh(View view){
        Intent intent=new Intent(SplashScreen.this,SplashScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(intent);
    }

    private void CheckArrayList(){

        Log.d("TAG","CRIMINAL SIZE: "+arrayList_criminal.size());
        Log.d("TAG","PO SIZE: "+arrayList_po.size());
        Log.d("TAG","Forece SIZE: "+arrayList_force.size());
        Log.d("TAG","CRIMe SIZE: "+arrayList_crime.size());

        if(!arrayList_criminal.isEmpty() && !arrayList_po.isEmpty() && !arrayList_force.isEmpty() && !arrayList_crime.isEmpty()){

        Log.d("TAG","gO TO NEW Activity");

            if(isCounterFinish){
                Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                intent.putExtra("arraylist_criminal",arrayList_criminal);
                intent.putExtra("arraylist_po",arrayList_po);
                intent.putExtra("arraylist_force",arrayList_force);
                intent.putExtra("arraylist_crime",arrayList_crime);
                intent.putExtra("splashScreen",true);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(intent);
            }
        }
    }

}
