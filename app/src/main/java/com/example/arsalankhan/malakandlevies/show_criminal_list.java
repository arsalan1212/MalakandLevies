package com.example.arsalankhan.malakandlevies;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.arsalankhan.malakandlevies.helper.CrimeModel;
import com.example.arsalankhan.malakandlevies.helper.CriminalModel;
import com.example.arsalankhan.malakandlevies.helper.POModel;
import com.example.arsalankhan.malakandlevies.helper.forceModel;
import com.example.arsalankhan.malakandlevies.model.CriminalAdapter;

import java.util.ArrayList;

public class show_criminal_list extends AppCompatActivity implements SearchView.OnQueryTextListener,CriminalAdapter.Communicator {

    private RecyclerView mRecyclerView;
    private ArrayList<CriminalModel> arrayList_criminal=new ArrayList<>();
    private ArrayList<CrimeModel> arrayList_crime_details=new ArrayList<>();
    private ArrayList<POModel> arrayList_po=new ArrayList<>();
    private ArrayList<forceModel> arrayList_force=new ArrayList<>();
    private CriminalAdapter adapter;
    private LinearLayout layout_search;
    private SearchView searchView;
    private Spinner spinner_criminal;
    private String spinner_value;
    private TextView tv_no_record_found;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_criminal_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.criminal_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Activity Enter Animation
        AnimateActivity();

        // initialization the views
        initView();

        //getting the intent data
        getIntentData();

        //set the adapter
        setRecyclerViewAdapter();


    }

    private void AnimateActivity() {

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Slide slide=new Slide();
            slide.setSlideEdge(Gravity.RIGHT);
            slide.setInterpolator(new AccelerateDecelerateInterpolator());
            slide.setDuration(500);
            getWindow().setEnterTransition(slide);
        }
    }

    //getting the intent data
    private void getIntentData() {
        Intent intent=getIntent();

        if(intent!=null){
            arrayList_criminal= (ArrayList<CriminalModel>) intent.getSerializableExtra("arraylist_criminal");
            arrayList_crime_details= (ArrayList<CrimeModel>) intent.getSerializableExtra("arraylist_crime");
            arrayList_po= (ArrayList<POModel>) intent.getSerializableExtra("arraylist_po");
            arrayList_force= (ArrayList<forceModel>) intent.getSerializableExtra("arraylist_force");
        }
    }

    // setting the Adapter
    private void setRecyclerViewAdapter() {
        adapter=new CriminalAdapter(show_criminal_list.this,arrayList_criminal);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);
    }

    private void initView() {
        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerview_criminal);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchView= (SearchView) findViewById(R.id.searchView);
        layout_search= (LinearLayout) findViewById(R.id.layout_search);
        spinner_criminal= (Spinner) findViewById(R.id.search_spinner);
        tv_no_record_found = (TextView) findViewById(R.id.tv_no_record_criminal);

        final String []criminal_search=getResources().getStringArray(R.array.criminal_search);

        ArrayAdapter<String> array_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,criminal_search);

        spinner_criminal.setAdapter(array_adapter);
        spinner_criminal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               spinner_value= criminal_search[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(this);

    }

    public void BackButton(View view){
        Intent intent=new Intent(show_criminal_list.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.putExtra("arraylist_criminal_activity",arrayList_criminal);
        intent.putExtra("arraylist_crime_activity",arrayList_crime_details);
        intent.putExtra("arraylist_po_activity",arrayList_po);
        intent.putExtra("arraylist_force_activity",arrayList_force);

        intent.putExtra("activity",true);
        finish();
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(show_criminal_list.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.putExtra("arraylist_criminal_activity",arrayList_criminal);
        intent.putExtra("arraylist_crime_activity",arrayList_crime_details);
        intent.putExtra("arraylist_po_activity",arrayList_po);
        intent.putExtra("arraylist_force_activity",arrayList_force);

        intent.putExtra("activity",true);
        finish();
        startActivity(intent);
    }

//searchView method
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }
//searcVeiw method
    @Override
    public boolean onQueryTextChange(String newText) {
        boolean found=false;
        ArrayList<CriminalModel> filterList=new ArrayList<>();
        if(!newText.equals("")){
            newText=newText.toLowerCase();
            if(spinner_value.equals("Name")){

                searchView.setQueryHint("Search");

                for(int i=0;i<arrayList_criminal.size();i++){
                    String name=arrayList_criminal.get(i).getName().toLowerCase();

                    if(name.contains(newText)){
                        found=true;
                        filterList.add(arrayList_criminal.get(i));
                    }
                }
            }else if(spinner_value.equals("Nic")){
                searchView.setQueryHint("Search");

                for(CriminalModel criminalModel:arrayList_criminal){
                    String nic=criminalModel.getNic().toLowerCase();
                    if(nic.contains(newText)){
                        found=true;
                        filterList.add(criminalModel);
                    }
                }
            }else if(spinner_value.equals("District")){
                searchView.setQueryHint("Search");

                for(CriminalModel criminalModel:arrayList_criminal){
                    String district=criminalModel.getDistrict().toLowerCase();
                    if(district.contains(newText)){
                        found=true;
                        filterList.add(criminalModel);
                    }
                }
            }

            //if in the filterList there is no data (means no record found)
            if(filterList.size()!=0){
                tv_no_record_found.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
            }
            else if(!found && !newText.equals("")){
                adapter.setFilter(arrayList_criminal);
                tv_no_record_found.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.VISIBLE);
            }
            else{
                tv_no_record_found.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
            }
            adapter.setFilter(filterList);
        }else {
            tv_no_record_found.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            adapter.setFilter(arrayList_criminal);
        }
        return true;
    }

    @Override
    public void messanger(View itemView,View transition_imageView,View transition_textView,int position,ArrayList<CriminalModel>arrayList_filter) {

        Intent intent=new Intent(show_criminal_list.this,CriminalCompleteDetail.class);

        //after this passing data to detail activity
        String criminal_name=arrayList_filter.get(position).getName();
        String criminal_image=arrayList_filter.get(position).getImage();
        ArrayList<CrimeModel> arrayList_temp=new ArrayList<>();
        String criminal_id=arrayList_filter.get(position).getId();
        for(int i=0;i<arrayList_crime_details.size();i++) {
            if (criminal_id.equals(arrayList_crime_details.get(i).getCriminal_id())) {
               arrayList_temp.add(arrayList_crime_details.get(i));
            }
        }


                //means criminal crime  record is present

        //setting the criminal detail
        intent.putExtra("criminal_name",criminal_name);
        intent.putExtra("criminal_image",criminal_image);
        intent.putExtra("position",position);
        intent.putExtra("criminal_arraylist",arrayList_filter);
        intent.putExtra("crime_detail_arraylist",arrayList_temp);

        //checking if version > 21 then do shared transition animation else start activity
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            //for transition animation
            Pair[] pairs=new Pair[2];
            pairs[0]=new Pair<View,String>(transition_imageView,"transition_criminal_image");
            pairs[1]=new Pair<View,String>(itemView,"singlerow");
            ActivityOptionsCompat options=ActivityOptionsCompat.makeSceneTransitionAnimation(this,pairs);
            startActivity(intent,options.toBundle());
        }
        else{
            startActivity(intent);
            }



    }
}
