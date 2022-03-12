package com.example.promodoro.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.promodoro.AddNewTaf;
import com.example.promodoro.MainActivity;
import com.example.promodoro.Model.JsonDataMaker;
import com.example.promodoro.Model.TafModel;
import com.example.promodoro.Model.TaskModel;
import com.example.promodoro.R;
import com.example.promodoro.TafDetailsActivity;
import com.google.gson.Gson;

import java.util.List;

public class TafAdapter extends RecyclerView.Adapter<TafAdapter.ViewHolder> {

    private List<TafModel> tafList;
    private final MainActivity activity;
    private JsonDataMaker dataManager;

    public TafAdapter(MainActivity activity){

        this.activity = activity;

        dataManager = new JsonDataMaker(activity);
        tafList = dataManager.getJsonData();

    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.taf_layout, parent, false);
        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position){

        if(tafList.size() != 0){
            int thePosition = position;
            TafModel item = tafList.get(position);
            holder.myTafText.setText(item.getTafName());
            holder.myTafCheckBox.setChecked(toBolean(item.getStatus()));
            holder.myTafText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, TafDetailsActivity.class);
                    intent.putExtra("id", String.valueOf(thePosition));
                    activity.startActivity(intent);
                }
            });
        }

    }

    public int getItemCount(){
        return tafList.size();
    }

    private boolean toBolean(int n){
        return n != 0;
    }

    public void setTafs(List<TafModel> tafList){
        this.tafList = tafList;
        notifyDataSetChanged();
    }

    public Context getContext(){return activity;}

    public void deleteItem(int position){

        //TafModel item = tafList.get(position);
        //on supprime

        tafList.remove(position);

        Gson gson = new Gson();

        dataManager.writeJson(gson.toJson(tafList));

        notifyDataSetChanged();

        notifyItemRemoved(position);

    }

    public void editItem(int position){

        TafModel item = tafList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", position);
        bundle.putString("tafName", item.getTafName());
        AddNewTaf fragment = new AddNewTaf(activity);
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewTaf.TAG);

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
