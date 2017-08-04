package com.example.arsalankhan.malakandlevies.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arsalankhan.malakandlevies.R;
import com.example.arsalankhan.malakandlevies.helper.forceModel;

import java.util.ArrayList;

/**
 * Created by Arsalan khan on 2/10/2017.
 */

public class ForceAdapter extends RecyclerView.Adapter<ForceAdapter.MyViewHolder> {

    private ArrayList<forceModel>mForceArrayList=new ArrayList<>();
    Context context;
    View view;
    int previouspostion=-1;
    public ForceAdapter(Context context, ArrayList<forceModel> arrayList){
        mForceArrayList=arrayList;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.single_row_force,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mForceName.setText(mForceArrayList.get(position).getForceName());
        holder.mForceRegNumber.setText(mForceArrayList.get(position).getForceRegNumber());
        holder.mForceRank.setText(mForceArrayList.get(position).getForceRank());


       /* if (position > previouspostion) {
            AnimationUtils.animator(holder, true);
        } else {
            AnimationUtils.animator(holder, false);
        }\
        previouspostion = position;*/
    }

    @Override
    public int getItemCount() {
        return mForceArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mForceName,mForceRegNumber,mForceRank;
        public MyViewHolder(View itemView) {
            super(itemView);
            mForceName= (TextView) itemView.findViewById(R.id.tv_force_name);
            mForceRegNumber= (TextView) itemView.findViewById(R.id.tv_force_reg_no);
            mForceRank= (TextView) itemView.findViewById(R.id.tv_force_rank);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    public void setFilter(ArrayList<forceModel> newList){
        mForceArrayList=new ArrayList<>();
        mForceArrayList.addAll(newList);
        notifyDataSetChanged();
    }
}
