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
import com.example.arsalankhan.malakandlevies.helper.POModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Arsalan khan on 2/18/2017.
 */

public class POAdapter extends RecyclerView.Adapter<POAdapter.MyViewHolder> {

    ArrayList<POModel> arrayList_po = new ArrayList<>();
    private Context context;
    private static final String image_url = AppUrl.criminal_image;
    private POCommunicator communicator;
    int previouspostion = -1;

    public POAdapter(Context context, ArrayList<POModel> arrayList) {
        arrayList_po = arrayList;
        this.context = context;
        communicator = (POCommunicator) context;

    }

    @Override
    public POAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_row_criminal_po_list, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final POAdapter.MyViewHolder holder, int position) {
        POModel POModel = arrayList_po.get(position);

        holder.tv_po_name.setText(POModel.getName());
        Picasso.with(context).load(image_url + POModel.getImage()).into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                holder.progressBar.setVisibility(View.GONE);
            }
        });

        holder.tv_po_father_name.setText(POModel.getFather_name());

        holder.tv_po_nic.setText(POModel.getNic());

        holder.tv_po_phone_number.setText(POModel.getPhone_no());

      /*  if (position > previouspostion) {
            AnimationUtils.animator(holder, true);
        } else {
            AnimationUtils.animator(holder, false);
        }
        previouspostion = position;*/
    }

    @Override
    public int getItemCount() {
        return arrayList_po.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_po_name, tv_po_father_name, tv_po_nic;
        TextView tv_po_phone_number;
        CircleImageView imageView;
        ProgressBar progressBar;


        public MyViewHolder(final View itemView) {
            super(itemView);
            tv_po_name = (TextView) itemView.findViewById(R.id.tv_criminal_name);
            tv_po_father_name = (TextView) itemView.findViewById(R.id.tv_criminal_father_name);
            tv_po_nic = (TextView) itemView.findViewById(R.id.tv_criminal_nic);
            tv_po_phone_number = (TextView) itemView.findViewById(R.id.tv_criminal_phone_no);
            imageView = (CircleImageView) itemView.findViewById(R.id.profile_image);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    communicator.messanger(itemView, imageView, tv_po_name, getAdapterPosition(), arrayList_po);
                }
            });
        }

    }

    public void setFilter(ArrayList<POModel> newList) {
        arrayList_po = new ArrayList<>();
        arrayList_po.clear();
        arrayList_po.addAll(newList);
        notifyDataSetChanged();
    }

    public interface POCommunicator {
        public void messanger(View itemView, View image, View textView, int position, ArrayList<POModel> arrayList_filter);
    }
}

