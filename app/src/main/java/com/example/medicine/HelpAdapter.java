package com.example.medicine;

import android.content.Context;
import android.graphics.ImageDecoder;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HelpAdapter extends RecyclerView.Adapter<HelpAdapter.MyViewHolder> {
    Context context;
    ArrayList  Name, disc ;
    ImageView imageView;
    HelpAdapter(Context context, ArrayList Name,ArrayList disc ){

        this.context = context;
        this.Name =Name;
        this.disc = disc;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(context);
        View view  = inflater.inflate(R.layout.medicine_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.medicen_name_txt.setText(String.valueOf(Name.get(position)));
        holder.medicen_disc_txt.setText(String.valueOf(disc.get(position)));

    }

    @Override
    public int getItemCount() {
        return Name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

       TextView  medicen_name_txt, medicen_disc_txt;

       ImageView medicen_image;
       View view_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            medicen_name_txt =itemView.findViewById(R.id.medicine_name);
            medicen_image = itemView.findViewById(R.id.medicine_image);
            medicen_disc_txt = itemView.findViewById(R.id.medicine_discription);


        }
    }
}
