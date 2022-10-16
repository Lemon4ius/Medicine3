package com.example.medicine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class medicineAdapter extends RecyclerView.Adapter<medicineAdapter.MedicineHolder> {

    private List<Medicine> medicine;

    public   medicineAdapter(List<Medicine> medicine){
        this.medicine=medicine;
    }

    @NonNull
    @Override
    public MedicineHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.medicine_view, viewGroup,false);
        return new MedicineHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineHolder viewHolder, int i) {

      Medicine medicines=  medicine.get(i);
      viewHolder.bind(medicines);
    }

    @Override
    public int getItemCount() {
        return medicine.size();
    }

    class MedicineHolder extends RecyclerView.ViewHolder{

            private ImageView imageView;
            private TextView discriptionview;
            private TextView nameview;


        public MedicineHolder(@NonNull View itemView) {
            super(itemView);

                imageView=itemView.findViewById(R.id.medicine_image);
                discriptionview = itemView.findViewById(R.id.medicine_discription);
                nameview = itemView.findViewById(R.id.medicine_name);
        }
        public  void bind(Medicine medicine){

            imageView.setImageResource(medicine.poster);
            nameview.setText(medicine.name);
            discriptionview.setText(medicine.discription);

        }
    }
}
