package com.example.promodoro.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.promodoro.MainActivity;
import com.example.promodoro.Model.TafModel;
import com.example.promodoro.Model.TaskModel;
import com.example.promodoro.R;

import java.util.List;

public class TafAdapter extends RecyclerView.Adapter<TafAdapter.ViewHolder> {

    private List<TafModel> tafList;
    private MainActivity activity;

    public TafAdapter(MainActivity activity){
        this.activity = activity;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.taf_layout, parent, false);
        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position){

        TafModel item = tafList.get(position);
        holder.myTafText.setText(item.getTafName());
        holder.myTafCheckBox.setChecked(toBolean(item.getStatus()));

    }

    public int getItemCount(){
        return tafList.size();
    }

    private boolean toBolean(int n){
        return n != 0;
    }

    public void setTaf(List<TafModel> tafList){
        this.tafList = tafList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView myTafText;
        CheckBox myTafCheckBox;
        ViewHolder(View view){
            super(view);
            myTafText = view.findViewById(R.id.myTafText);
            myTafCheckBox = view.findViewById(R.id.myTafCheckBox);
        }
    }



}
