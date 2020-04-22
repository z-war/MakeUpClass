package com.example.makeupclass;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.makeupclass.ModelClass;
import com.example.makeupclass.R;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolder>{
    private ArrayList<ModelClass> objectmodelclassarrraylist;
    public AdapterClass(ArrayList<ModelClass> a)
    {
       this. objectmodelclassarrraylist = a;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View single_row = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        ViewHolder objectview = new ViewHolder(single_row);
        return objectview;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(objectmodelclassarrraylist.get(position).getName());
        holder.location.setText(objectmodelclassarrraylist.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return objectmodelclassarrraylist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name , location;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.single_row_nameTV);
            location = itemView.findViewById(R.id.single_row_locTV);
        }
    }
}
