package com.example.medicine;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HelpAdapter extends RecyclerView.Adapter<HelpAdapter.MyViewHolder> {
    Context context;
    ArrayList Name, price;
    ArrayList<String> imageView = new ArrayList<String>();

    private static ClickListener clickListener;

    HelpAdapter(Context context, ArrayList Name, ArrayList imageView, ArrayList price, ClickListener clickListener) {
        this.context = context;
        this.Name = Name;
        this.imageView = imageView;
        this.price = price;
        this.clickListener = clickListener;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.medicen_name_txt.setText(String.valueOf(Name.get(position)));
        holder.medidcine_price_txt.setText(String.valueOf(price.get(position)));
        Uri uri = Uri.parse(imageView.get(position));
        holder.medicen_image_txt.setImageURI(uri);
    }

    private Context getBaseContext() {
        return context;
    }


    @Override
    public int getItemCount() {
        return Name.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView medicen_name_txt, medicen_disc_txt, medidcine_price_txt;

        ImageView medicen_image_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            medidcine_price_txt = itemView.findViewById(R.id.medicine_price);
            medicen_name_txt = itemView.findViewById(R.id.medicine_name);
            medicen_image_txt = itemView.findViewById(R.id.medicine_image);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(),view);
        }
    }

    public interface ClickListener {
        void onItemClick(int position, View v);

    }
}
