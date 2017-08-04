package com.example.arsalankhan.malakandlevies.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.arsalankhan.malakandlevies.AppUrl;
import com.example.arsalankhan.malakandlevies.R;
import com.example.arsalankhan.malakandlevies.helper.CriminalModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Arsalan khan on 2/14/2017.
 */

public class CriminalAdapter extends RecyclerView.Adapter<CriminalAdapter.MyViewHolder> {

    ArrayList<CriminalModel> arrayList_criminal=new ArrayList<>();
    private Context context;
    private static final String image_url= AppUrl.criminal_image;
    private Communicator communicator;
    int previousposition=-1;

    public CriminalAdapter(Context context,ArrayList<CriminalModel> arrayList){
        arrayList_criminal=arrayList;
        this.context=context;
        communicator= (Communicator) context;
    }

    @Override
    public CriminalAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.single_row_criminal_po_list,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final CriminalAdapter.MyViewHolder holder, int position) {
        CriminalModel criminalModel=arrayList_criminal.get(position);

        holder.tv_criminal_name.setText(criminalModel.getName());
        Picasso.with(context).load(image_url+criminalModel.getImage()).into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                holder.progressBar.setVisibility(View.GONE);
            }
        });
            holder.tv_criminal_father_name.setText(criminalModel.getFather_name());

            holder.tv_criminal_nic.setText(criminalModel.getNic());

            holder.tv_criminal_phone_number.setText(criminalModel.getPhone_no());
/*
        if(position>previousposition){
            AnimationUtils.animator(holder,true);
        }else{
            AnimationUtils.animator(holder,false);
        }
        previousposition=position;*/
    }

    @Override
    public int getItemCount() {
        return arrayList_criminal.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_criminal_name,tv_criminal_father_name,tv_criminal_nic;
        TextView tv_criminal_phone_number;
        CircleImageView imageView;
        ProgressBar progressBar;
        public MyViewHolder(final View itemView) {
            super(itemView);
            tv_criminal_name= (TextView) itemView.findViewById(R.id.tv_criminal_name);
            tv_criminal_father_name= (TextView) itemView.findViewById(R.id.tv_criminal_father_name);
            tv_criminal_nic= (TextView) itemView.findViewById(R.id.tv_criminal_nic);
            tv_criminal_phone_number= (TextView) itemView.findViewById(R.id.tv_criminal_phone_no);
            imageView= (CircleImageView) itemView.findViewById(R.id.profile_image);
            progressBar= (ProgressBar) itemView.findViewById(R.id.progress);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    communicator.messanger(itemView,imageView,tv_criminal_name,getAdapterPosition(),arrayList_criminal);
                }
            });
        }
    }

    public void setFilter(ArrayList<CriminalModel> newList){

        arrayList_criminal=new ArrayList<>();
        arrayList_criminal.clear();
        arrayList_criminal.addAll(newList);
        notifyDataSetChanged();
    }

    public interface Communicator{
        public void messanger(View itemView,View image,View textView,int position,ArrayList<CriminalModel> arrayList_filter);
    }
}
