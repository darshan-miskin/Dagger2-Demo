package com.gne.www.dagger2demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.gne.www.dagger2demo.retrofit.model.ResponseData;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.RecyclerHolder> {

    ArrayList<ResponseData> responseDataArrayList = new ArrayList<>();
    AppCompatActivity activity;

    public AdapterRecycler(ArrayList<ResponseData> responseDataArrayList, AppCompatActivity activity) {
        this.responseDataArrayList.addAll(responseDataArrayList);
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_recyclerview, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        holder.txtProductName.setText(responseDataArrayList.get(position).getProduct_name());
    }

    @Override
    public int getItemCount() {
        return responseDataArrayList.size();
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {

        TextView txtProductName;
        RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            txtProductName=itemView.findViewById(R.id.txt_product_name);

            txtProductName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(activity.findViewById(android.R.id.content),"Supplied by "+
                            responseDataArrayList.get(getAdapterPosition()).getSupplier()+" for Rs. "+
                            responseDataArrayList.get(getAdapterPosition()).getUnit_cost(),Snackbar.LENGTH_LONG).show();
                }
            });
        }
    }
}
