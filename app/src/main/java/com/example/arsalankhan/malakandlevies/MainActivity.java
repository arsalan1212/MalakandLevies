package com.example.arsalankhan.malakandlevies;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.arsalankhan.malakandlevies.helper.CrimeModel;
import com.example.arsalankhan.malakandlevies.helper.CriminalModel;
import com.example.arsalankhan.malakandlevies.helper.POModel;
import com.example.arsalankhan.malakandlevies.helper.forceModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<CriminalModel>arrayList_criminal=new ArrayList<>();
    ArrayList<POModel>arrayList_po=new ArrayList<>();
    ArrayList<forceModel>arrayList_force=new ArrayList<>();
    ArrayList<CrimeModel>arrayList_crime=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // getting intent from Splash Screen
        getIntentData();

        //getting intent data from Criminal Activity
        getIntentDataFromActivity();


    }

    //getting the intent data from SplashScreen
    private void getIntentData() {

        Intent intent=getIntent();
        boolean isSplashScreen=intent.getBooleanExtra("splashScreen",false);

        if(isSplashScreen){
            arrayList_criminal= (ArrayList<CriminalModel>) intent.getSerializableExtra("arraylist_criminal");
            arrayList_po= (ArrayList<POModel>) intent.getSerializableExtra("arraylist_po");
            arrayList_force= (ArrayList<forceModel>) intent.getSerializableExtra("arraylist_force");
            arrayList_crime= (ArrayList<CrimeModel>) intent.getSerializableExtra("arraylist_crime");
        }
    }



    //getting intent data from Activity other than Splash screen
    private void getIntentDataFromActivity() {

        Intent intent=getIntent();
        boolean isActivity=intent.getBooleanExtra("activity",false);

        if(isActivity){
            arrayList_criminal= (ArrayList<CriminalModel>) intent.getSerializableExtra("arraylist_criminal_activity");
            arrayList_crime= (ArrayList<CrimeModel>) intent.getSerializableExtra("arraylist_crime_activity");
            arrayList_po= (ArrayList<POModel>) intent.getSerializableExtra("arraylist_po_activity");
            arrayList_force= (ArrayList<forceModel>) intent.getSerializableExtra("arraylist_force_activity");
        }
    }


    public void show_force_info(View view){
        Intent intent=new Intent(MainActivity.this,show_force_list.class);

        intent.putExtra("arraylist_force",arrayList_force);
        intent.putExtra("arraylist_criminal",arrayList_criminal);
        intent.putExtra("arraylist_crime",arrayList_crime);
        intent.putExtra("arraylist_po",arrayList_po);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            ActivityOptionsCompat option=ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this);
            startActivity(intent,option.toBundle());
        }
        else{
            startActivity(intent);
        }
    }


    public void show_criminal(View view){
        Intent intent=new Intent(MainActivity.this,show_criminal_list.class);

        intent.putExtra("arraylist_force",arrayList_force);
        intent.putExtra("arraylist_criminal",arrayList_criminal);
        intent.putExtra("arraylist_crime",arrayList_crime);
        intent.putExtra("arraylist_po",arrayList_po);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            ActivityOptionsCompat option=ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this);
            startActivity(intent,option.toBundle());
        }
        else{
            startActivity(intent);
        }

    }

    public void show_po(View view){
        Intent intent=new Intent(MainActivity.this,show_po_list.class);

        intent.putExtra("arraylist_force",arrayList_force);
        intent.putExtra("arraylist_criminal",arrayList_criminal);
        intent.putExtra("arraylist_crime",arrayList_crime);
        intent.putExtra("arraylist_po",arrayList_po);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            ActivityOptionsCompat option=ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this);
            startActivity(intent,option.toBundle());
        }
        else{
            startActivity(intent);
        }
    }
    public void quit(View view){
        finish();
    }


}
