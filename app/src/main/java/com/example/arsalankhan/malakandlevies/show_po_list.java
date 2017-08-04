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
import android.transition.Explode;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.arsalankhan.malakandlevies.helper.CrimeModel;
import com.example.arsalankhan.malakandlevies.helper.CriminalModel;
import com.example.arsalankhan.malakandlevies.helper.POModel;
import com.example.arsalankhan.malakandlevies.helper.forceModel;
import com.example.arsalankhan.malakandlevies.model.POAdapter;

import java.util.ArrayList;

import static com.example.arsalankhan.malakandlevies.Dialog.progressDialog.dialog;

public class show_po_list extends AppCompatActivity implements SearchView.OnQueryTextListener,POAdapter.POCommunicator{

    private RecyclerView mRecyclerView;

    private ArrayList<forceModel> arrayList_force=new ArrayList<>();
    private ArrayList<CriminalModel> arrayList_criminal=new ArrayList<>();
    private ArrayList<POModel> arrayList_po=new ArrayList<>();
    private ArrayList<CrimeModel> arrayList_crime_details=new ArrayList<>();

    private POAdapter adapter;
    private LinearLayout layout_search;
    private SearchView searchView;
    private Spinner spinner_po;
    private String spinner_value;
    private TextView tv_no_record_found;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_po_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.po_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //setting activity enter animation
        setActivityAnimation();

        // initialization the views
            initView();

        //getting intent data
        getIntentData();

        //setting the recycler View adapter
        setRecyclerViewAdapter();



    }

    private void setActivityAnimation() {

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Explode explode=new Explode();
            explode.setDuration(500);
            explode.setInterpolator(new AnticipateInterpolator());
            getWindow().setEnterTransition(explode);
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


    //setting the recycler view adapter
    private void setRecyclerViewAdapter() {
        adapter=new POAdapter(show_po_list.this, arrayList_po);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);
    }

    private void initView() {
        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerview_po);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchView= (SearchView) findViewById(R.id.searchView_PO);
        layout_search= (LinearLayout) findViewById(R.id.layout_search);
        spinner_po = (Spinner) findViewById(R.id.search_spinner);
        tv_no_record_found = (TextView) findViewById(R.id.tv_no_record_po);

        final String[] po_search=getResources().getStringArray(R.array.criminal_search);

        ArrayAdapter<String> array_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,po_search);
        spinner_po.setAdapter(array_adapter);
        spinner_po.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner_value= po_search[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(this);
    }


    public void BackButton(View view){
        Intent intent=new Intent(show_po_list.this,MainActivity.class);
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
        Intent intent=new Intent(show_po_list.this,MainActivity.class);
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
        ArrayList<POModel> filterList=new ArrayList<>();
        boolean found=false;
        if(!newText.equals("")){
            newText=newText.toLowerCase();
            if(spinner_value.equals("Name")){

                searchView.setQueryHint("Search");

                for(POModel poModel: arrayList_po){
                    String name=poModel.getName().toLowerCase();

                    if(name.contains(newText)){
                        filterList.add(poModel);
                        found=true;
                    }
                }
            }else if(spinner_value.equals("Nic")){
                searchView.setQueryHint("Search");

                for(POModel poModel: arrayList_po){
                    String nic=poModel.getNic().toLowerCase();

                    if(nic.contains(newText)){
                        found=true;
                        filterList.add(poModel);
                    }
                }
            }else if(spinner_value.equals("District")){
                searchView.setQueryHint("Search");

                for(POModel poModel: arrayList_po){
                    String district=poModel.getDistrict().toLowerCase();

                    if(district.contains(newText)){
                        found=true;
                        filterList.add(poModel);
                    }
                }
            }


            //if in the filterList there is no data (means no record found)
            if(filterList.size()!=0){
                tv_no_record_found.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
            }
            else if(!found && !newText.equals("")){
                adapter.setFilter(arrayList_po);
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
            adapter.setFilter(arrayList_po);
        }

        return true;
    }

    @Override
    public void messanger(View itemView, View image, View textView, int position,ArrayList<POModel> arraylist_filter) {

        Intent intent=new Intent(show_po_list.this,POCompleteDetail.class);

        //after this passing data to detail activity
        String po_name=arraylist_filter.get(position).getName();
        String po_image=arraylist_filter.get(position).getImage();

        ArrayList<CrimeModel> arrayList_temp=new ArrayList<>();
        String criminal_id=arraylist_filter.get(position).getId();
        for(int i=0;i<arrayList_crime_details.size();i++) {
            if (criminal_id.equals(arrayList_crime_details.get(i).getCriminal_id())) {
                arrayList_temp.add(arrayList_crime_details.get(i));
            }
        }


        //means criminal crime  record is present

        //setting the criminal detail
        intent.putExtra("po_name",po_name);
        intent.putExtra("po_image",po_image);
        intent.putExtra("position",position);
        intent.putExtra("po_arraylist",arraylist_filter);
        intent.putExtra("crime_detail_arraylist",arrayList_temp);
        //checking if version > 21 then do shared transition animation else start activity
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            //for transition animation
            Pair[] pairs=new Pair[2];
            pairs[0]=new Pair<View,String>(image,"transition_criminal_image");
            pairs[1]=new Pair<View,String>(itemView,"singlerow");
            ActivityOptionsCompat options=ActivityOptionsCompat.makeSceneTransitionAnimation(this,pairs);
            if(dialog!=null){
                // dialog.hide();
                dialog.dismiss();
            }
            startActivity(intent,options.toBundle());
        }else{
            if(dialog!=null){
                // dialog.hide();
                dialog.dismiss();
            }
            startActivity(intent);
        }
    }
    }
