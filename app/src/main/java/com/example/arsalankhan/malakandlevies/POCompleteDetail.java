package com.example.arsalankhan.malakandlevies;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.arsalankhan.malakandlevies.helper.CrimeModel;
import com.example.arsalankhan.malakandlevies.helper.POModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class POCompleteDetail extends AppCompatActivity {

    private static final String image_url="http://ourwebacademy.com/levies/admin/criminal_images/";
    private ImageView imageView_po;
    private TextView tv_po_name,tv_crime_no_record;
    private EditText f_name, cnic, phone_no, district, address, court_name, status, status_details,comments,date_declare_po;
    private ArrayList<POModel> arrayList_po;
    private ArrayList<CrimeModel> arraylist_crime_detail;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_pocomplete_detail);


        Toolbar toolbar = (Toolbar) findViewById(R.id.po_detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //initialization the views
        initView();

        //getting intent  data
        getIntentData();

        //getting and setting the criminal detail
         getAndSetPODetail();


        //getting and setting the Criminal Crime detail
       getAndSetCrimeDetail();
    }


    // initializing the views
    private void initView() {

        tv_po_name= (TextView) findViewById(R.id.tv_po_nm);
        imageView_po= (ImageView) findViewById(R.id.po_image);
        f_name = (EditText) findViewById(R.id.et_father_name);
        cnic = (EditText) findViewById(R.id.et_cnic);
        phone_no = (EditText) findViewById(R.id.et_phone_no);
        district = (EditText) findViewById(R.id.et_district);
        address = (EditText) findViewById(R.id.et_address);
        court_name = (EditText) findViewById(R.id.et_court_name);
        status = (EditText) findViewById(R.id.et_status);
        status_details = (EditText) findViewById(R.id.et_status_details);
        comments = (EditText) findViewById(R.id.et_comments);
        date_declare_po= (EditText) findViewById(R.id.et_date_declare_po);
        tv_crime_no_record= (TextView) findViewById(R.id.tv_crime_no_record);
        arrayList_po=new ArrayList<>();
        arraylist_crime_detail=new ArrayList<>();

    }
//getting the intent data
    private void getIntentData() {
        Intent intent=getIntent();
        String name=intent.getStringExtra("po_name");
        String image=intent.getStringExtra("po_image");
        position = intent.getIntExtra("position",0);

        arrayList_po= (ArrayList<POModel>) intent.getSerializableExtra("po_arraylist");
        arraylist_crime_detail= (ArrayList<CrimeModel>) intent.getSerializableExtra("crime_detail_arraylist");

        //setting PO name and profile image
        tv_po_name.setText(name);
        Picasso.with(POCompleteDetail.this).load(image_url+image).into(imageView_po);
    }


    //setting and getting PO detail
    private void getAndSetPODetail() {

        f_name.setText(arrayList_po.get(position).getFather_name());
        cnic.setText(arrayList_po.get(position).getNic());
        phone_no.setText(arrayList_po.get(position).getPhone_no());
        district.setText(arrayList_po.get(position).getDistrict());
        address.setText(arrayList_po.get(position).getAddress());
        court_name.setText(arrayList_po.get(position).getCourt_name());
        date_declare_po.setText(arrayList_po.get(position).getDate_declare_po());
        status.setText(arrayList_po.get(position).getStatus());
        status_details.setText(arrayList_po.get(position).getStatus_detail());
        comments.setText(arrayList_po.get(position).getComments());

    }
    public void BackButton(View view){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            finishAfterTransition();
        }else{
            Intent intent=new Intent(POCompleteDetail.this,show_po_list.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(intent) ;
        }
    }

    @Override
    public void onBackPressed() {

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            finishAfterTransition();
        }else{
            Intent intent=new Intent(POCompleteDetail.this,show_po_list.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(intent) ;
        }

    }


    private void getAndSetCrimeDetail() {

        //layout for crimes
        LinearLayout crime_layout = (LinearLayout) findViewById(R.id.crime_layout);

        if(arraylist_crime_detail.size()<1)
        {
            tv_crime_no_record.setVisibility(View.VISIBLE);
        }
        else
        {
            tv_crime_no_record.setVisibility(View.GONE);
            for (int i = 0; i < arraylist_crime_detail.size(); i++) {

                //means criminal crime  record is present


                TextView crime_no = new TextView(this);
                crime_no.setText("Crime : " + (i + 1));
                crime_no.setTextColor(Color.parseColor("#ffffff"));
                crime_no.setBackgroundColor(Color.parseColor("#b0b0b0"));
                crime_no.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                crime_no.setPadding(10, 10, 10, 10);
                crime_no.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));


                // fir no
                TextView fir_no = new TextView(this);
                fir_no.setText("Fir No.");
                fir_no.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                LinearLayout.LayoutParams fir_no_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                fir_no_params.setMargins(0, 10, 0, 0);
                fir_no.setLayoutParams(fir_no_params);

                EditText efir_no = new EditText(this);
                efir_no.setEnabled(false);
                efir_no.setEms(15);
                efir_no.setText(arraylist_crime_detail.get(i).getFir_no());
                efir_no.setPadding(10, 10, 10, 10);
                efir_no.setBackgroundColor(Color.parseColor("#ffffff"));
                LinearLayout.LayoutParams efir_no_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                efir_no.setLayoutParams(efir_no_params);


                //fir date
                TextView fir_date = new TextView(this);
                fir_date.setText("Fir Date");
                fir_date.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                LinearLayout.LayoutParams fir_date_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                fir_date_params.setMargins(0, 10, 0, 0);
                fir_date.setLayoutParams(fir_date_params);

                EditText efir_date = new EditText(this);
                efir_date.setEnabled(false);
                efir_date.setEms(15);
                efir_date.setPadding(10, 10, 10, 10);
                efir_date.setText(arraylist_crime_detail.get(i).getFir_date());
                efir_date.setBackgroundColor(Color.parseColor("#ffffff"));
                LinearLayout.LayoutParams efir_date_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                efir_date.setLayoutParams(efir_date_params);



                //fir section
                TextView fir_section = new TextView(this);
                fir_section.setText("Fir Section");
                fir_section.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                LinearLayout.LayoutParams fir_section_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                fir_section_params.setMargins(0, 10, 0, 0);
                fir_section.setLayoutParams(fir_section_params);

                EditText efir_section = new EditText(this);
                efir_section.setEnabled(false);
                efir_section.setEms(15);
                efir_section.setPadding(10, 10, 10, 10);
                efir_section.setText(arraylist_crime_detail.get(i).getFir_section());
                efir_section.setBackgroundColor(Color.parseColor("#ffffff"));
                LinearLayout.LayoutParams efir_section_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                efir_section.setLayoutParams(efir_section_params);


                //investigation officer
                TextView inv_officer = new TextView(this);
                inv_officer.setText("Investigation Officer");
                inv_officer.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                LinearLayout.LayoutParams inv_officer_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                inv_officer_params.setMargins(0, 10, 0, 0);
                inv_officer.setLayoutParams(inv_officer_params);

                EditText einv_officer = new EditText(this);
                einv_officer.setEnabled(false);
                einv_officer.setEms(15);
                einv_officer.setPadding(10, 10, 10, 10);
                einv_officer.setText(arraylist_crime_detail.get(i).getInv_officer());
                einv_officer.setBackgroundColor(Color.parseColor("#ffffff"));
                LinearLayout.LayoutParams einv_officer_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                einv_officer.setLayoutParams(einv_officer_params);

                //complainant name
                TextView comp_name = new TextView(this);
                comp_name.setText("Complainant Name");
                comp_name.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                LinearLayout.LayoutParams comp_name_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                comp_name_params.setMargins(0, 10, 0, 0);
                comp_name.setLayoutParams(comp_name_params);

                EditText ecomp_name = new EditText(this);
                ecomp_name.setEnabled(false);
                ecomp_name.setEms(15);
                ecomp_name.setPadding(10, 10, 10, 10);
                ecomp_name.setText(arraylist_crime_detail.get(i).getComp_name());
                ecomp_name.setBackgroundColor(Color.parseColor("#ffffff"));
                LinearLayout.LayoutParams ecomp_name_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                ecomp_name.setLayoutParams(ecomp_name_params);


                //Accused name
                TextView acc_name = new TextView(this);
                acc_name.setText("Accused Name");
                acc_name.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                LinearLayout.LayoutParams acc_name_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                acc_name_params.setMargins(0, 10, 0, 0);
                acc_name.setLayoutParams(acc_name_params);

                EditText eacc_name = new EditText(this);
                eacc_name.setEnabled(false);
                eacc_name.setEms(15);
                eacc_name.setPadding(10, 10, 10, 10);
                eacc_name.setText(arraylist_crime_detail.get(i).getAcc_name());
                eacc_name.setBackgroundColor(Color.parseColor("#ffffff"));
                LinearLayout.LayoutParams eacc_name_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                eacc_name.setLayoutParams(eacc_name_params);


                //Date of arrest
                TextView date_of_arrest = new TextView(this);
                date_of_arrest.setText("Date of Arrest");
                date_of_arrest.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                LinearLayout.LayoutParams date_of_arrest_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                date_of_arrest_params.setMargins(0, 10, 0, 0);
                date_of_arrest.setLayoutParams(date_of_arrest_params);

                EditText edate_of_arrest = new EditText(this);
                edate_of_arrest.setEnabled(false);
                edate_of_arrest.setEms(15);
                edate_of_arrest.setPadding(10, 10, 10, 10);
                edate_of_arrest.setText(arraylist_crime_detail.get(i).getDate_of_arrest());
                edate_of_arrest.setBackgroundColor(Color.parseColor("#ffffff"));
                LinearLayout.LayoutParams edate_of_arrest_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                edate_of_arrest.setLayoutParams(edate_of_arrest_params);

                //Crime
                TextView crime = new TextView(this);
                crime.setText("Crime");
                crime.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                LinearLayout.LayoutParams crime_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                crime_params.setMargins(0, 10, 0, 0);
                crime.setLayoutParams(crime_params);

                EditText ecrime = new EditText(this);
                ecrime.setEnabled(false);
                ecrime.setEms(15);
                ecrime.setPadding(10, 10, 10, 10);
                ecrime.setText(arraylist_crime_detail.get(i).getCrime());
                ecrime.setBackgroundColor(Color.parseColor("#ffffff"));
                LinearLayout.LayoutParams ecrime_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                ecrime.setLayoutParams(ecrime_params);

                //History
                TextView history = new TextView(this);
                history.setText("History");
                history.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                LinearLayout.LayoutParams history_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                history_params.setMargins(0, 10, 0, 0);
                history.setLayoutParams(history_params);

                EditText ehistory = new EditText(this);
                ehistory.setEnabled(false);
                ehistory.setEms(15);
                ehistory.setPadding(10, 10, 10, 10);
                ehistory.setText(arraylist_crime_detail.get(i).getHistory());
                ehistory.setBackgroundColor(Color.parseColor("#ffffff"));
                LinearLayout.LayoutParams ehistory_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                ehistory.setLayoutParams(ehistory_params);

                //Details
                TextView details = new TextView(this);
                details.setText("Details");
                details.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                LinearLayout.LayoutParams details_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                details_params.setMargins(0, 10, 0, 0);
                details.setLayoutParams(details_params);

                EditText edetails = new EditText(this);
                edetails.setEnabled(false);
                edetails.setEms(15);
                edetails.setPadding(10, 10, 10, 10);
                edetails.setText(arraylist_crime_detail.get(i).getDetails());
                edetails.setBackgroundColor(Color.parseColor("#ffffff"));
                LinearLayout.LayoutParams edetails_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                edetails.setLayoutParams(edetails_params);

                //Comments
                TextView comments = new TextView(this);
                comments.setText("Comments");
                comments.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                LinearLayout.LayoutParams comments_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                comments_params.setMargins(0, 10, 0, 0);
                comments.setLayoutParams(comments_params);

                EditText ecomments = new EditText(this);
                ecomments.setEnabled(false);
                ecomments.setEms(15);
                ecomments.setPadding(10, 10, 10, 10);
                ecomments.setText(arraylist_crime_detail.get(i).getComments());
                ecomments.setBackgroundColor(Color.parseColor("#ffffff"));
                LinearLayout.LayoutParams ecomments_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                ecomments.setLayoutParams(ecomments_params);


                View separator = new View(this);
                separator.setBackgroundColor(Color.parseColor("#8e8e8e"));
                LinearLayout.LayoutParams separator_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 1);
                separator_params.setMargins(0, 20, 0, 20);
                separator.setLayoutParams(separator_params);


                //adding all textviews and edit texts to linear layout
                crime_layout.addView(crime_no);
                crime_layout.addView(fir_no);
                crime_layout.addView(efir_no);
                crime_layout.addView(fir_date);
                crime_layout.addView(efir_date);
                crime_layout.addView(fir_section);
                crime_layout.addView(efir_section);
                crime_layout.addView(inv_officer);
                crime_layout.addView(einv_officer);
                crime_layout.addView(comp_name);
                crime_layout.addView(ecomp_name);
                crime_layout.addView(acc_name);
                crime_layout.addView(eacc_name);
                crime_layout.addView(date_of_arrest);
                crime_layout.addView(edate_of_arrest);
                crime_layout.addView(crime);
                crime_layout.addView(ecrime);
                crime_layout.addView(history);
                crime_layout.addView(ehistory);
                crime_layout.addView(details);
                crime_layout.addView(edetails);
                crime_layout.addView(comments);
                crime_layout.addView(ecomments);
                crime_layout.addView(separator);
            }

        }
    }
}
