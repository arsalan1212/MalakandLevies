package com.example.arsalankhan.malakandlevies;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.arsalankhan.malakandlevies.helper.CrimeModel;
import com.example.arsalankhan.malakandlevies.helper.CriminalModel;
import com.example.arsalankhan.malakandlevies.helper.POModel;
import com.example.arsalankhan.malakandlevies.helper.forceModel;
import com.example.arsalankhan.malakandlevies.model.ForceAdapter;

import java.util.ArrayList;

import static com.example.arsalankhan.malakandlevies.R.id.tv_no_record_found;

public class show_force_list extends AppCompatActivity implements SearchView.OnQueryTextListener {

    RecyclerView mRecyclerView;
    private ArrayList<forceModel> arrayList_force=new ArrayList<>();
    private ArrayList<CriminalModel> arrayList_criminal=new ArrayList<>();
    private ArrayList<POModel> arrayList_po=new ArrayList<>();
    private ArrayList<CrimeModel> arrayList_crime_details=new ArrayList<>();
    TextView tv_No_record_found;
    ForceAdapter adapter;
    private SearchView searchView;
    LinearLayout layout_search;
    private Spinner spinner_force;
    private String spinner_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_force_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.force_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //set activity enter animation
        setActivityAnimation();

        //initializing the views
        initViews();

        //getting the intent data from MainActivity
        getIntentData();

        //setting the recycler view adapter
        setRecyclerViewAdapter();

    }

    private void setActivityAnimation() {

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Slide slide=new Slide();
            slide.setSlideEdge(Gravity.LEFT);
            slide.setInterpolator(new AnticipateOvershootInterpolator());
            slide.setDuration(500);
            getWindow().setEnterTransition(slide);
        }
    }

    //getting the intent data from MainActivity
    private void getIntentData() {
        Intent intent =getIntent();

        if(intent!=null){
            arrayList_criminal= (ArrayList<CriminalModel>) intent.getSerializableExtra("arraylist_criminal");
            arrayList_crime_details= (ArrayList<CrimeModel>) intent.getSerializableExtra("arraylist_crime");
            arrayList_po= (ArrayList<POModel>) intent.getSerializableExtra("arraylist_po");
            arrayList_force= (ArrayList<forceModel>) intent.getSerializableExtra("arraylist_force");

        }
    }

    //set the recycer view adapter
    private void setRecyclerViewAdapter() {

        adapter=new ForceAdapter(show_force_list.this,arrayList_force);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);
    }

    private void initViews() {

        tv_No_record_found= (TextView) findViewById(tv_no_record_found);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_force);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchView= (SearchView) findViewById(R.id.searchView_force);
        spinner_force= (Spinner) findViewById(R.id.search_spinner);
        layout_search= (LinearLayout) findViewById(R.id.layout_search);

        final String[] force_search=getResources().getStringArray(R.array.force_search);

        ArrayAdapter<String> adapter_force=new ArrayAdapter<String>(show_force_list.this,android.R.layout.simple_spinner_dropdown_item,force_search);

        spinner_force.setAdapter(adapter_force);
        spinner_force.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner_value=force_search[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(this);
    }


    public void BackButton(View view){
        Intent intent=new Intent(show_force_list.this,MainActivity.class);
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
        Intent intent=new Intent(show_force_list.this,MainActivity.class);
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
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        if(!newText.equals("")){
           newText=newText.toLowerCase();
            boolean found=false;
           ArrayList<forceModel> filterList =new ArrayList<>();

            if(spinner_value.equals("Name")) {

                for (forceModel forceModel : arrayList_force) {
                    String name = forceModel.getForceName().toLowerCase();

                    if (name.contains(newText)) {
                        found=true;
                        filterList.add(forceModel);
                    }
                }
            }
            else if(spinner_value.equals("Reg.NO")) {

                for (forceModel forceModel : arrayList_force) {
                    String reg_no = forceModel.getForceRegNumber().toLowerCase();

                    if (reg_no.contains(newText)) {
                        found=true;
                        filterList.add(forceModel);
                    }
                }
            }
            else if(spinner_value.equals("Rank")) {

                for (forceModel forceModel : arrayList_force) {
                    String rank = forceModel.getForceRank().toLowerCase();

                    if (rank.contains(newText)) {
                        found=true;
                        filterList.add(forceModel);
                    }
                }
            }

           if(filterList.size()!=0){
               tv_No_record_found.setVisibility(View.GONE);
               mRecyclerView.setVisibility(View.VISIBLE);
               layout_search.setVisibility(View.VISIBLE);
           }
           else if(!found && !newText.equals("")){
               adapter.setFilter(arrayList_force);
               tv_No_record_found.setVisibility(View.VISIBLE);
               mRecyclerView.setVisibility(View.VISIBLE);
           }
           else{
               tv_No_record_found.setVisibility(View.GONE);
               layout_search.setVisibility(View.VISIBLE);
               mRecyclerView.setVisibility(View.GONE);
           }
          adapter.setFilter(filterList);
       }else{
            tv_No_record_found.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
           adapter.setFilter(arrayList_force);

       }
        return true;
    }
}
