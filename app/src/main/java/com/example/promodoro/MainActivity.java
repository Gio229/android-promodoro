package com.example.promodoro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.promodoro.Adapter.TafAdapter;
import com.example.promodoro.Model.JsonDataMaker;
import com.example.promodoro.Model.TafModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogCloseListener {

    private static final String FILENAME = "myData.txt";

    private static final String FOLDERNAME = "dataPromodoro";

    private RecyclerView tafRecyclerView;
    private TafAdapter tafAdapter;
    private FloatingActionButton addButton;
    private TextView titre;

    private List<TafModel> tafList;
    private JsonDataMaker dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //tafList = new ArrayList<>(); 
        titre = findViewById(R.id.tafText);


        dataManager = new JsonDataMaker(this);


        tafList = dataManager.getJsonData();




        tafRecyclerView = findViewById(R.id.tafRecyclerView);
        tafRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tafAdapter = new TafAdapter(this);
        tafRecyclerView.setAdapter(tafAdapter);

        addButton = findViewById(R.id.toAdd);


        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new RecyclerItemTouchHelper(tafAdapter));

        itemTouchHelper.attachToRecyclerView(tafRecyclerView);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTaf.newInstance(MainActivity.this).show(getSupportFragmentManager(), AddNewTaf.TAG );
            }
        });

        /*addButton.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {

                //Toast.makeText(this, "Hello 123", 2000);
                titre.setText("marche");
                return false;
            }
        });*/

        //titre.setText(dataManager.getTextFromStorage());

    }

    @Override
    public void handleDialogClose(DialogInterface dialog){
        tafList = dataManager.getJsonData();
        //Collections.reverse(tafList);
        tafAdapter.setTafs(tafList);
        tafAdapter.notifyDataSetChanged();
    }



}