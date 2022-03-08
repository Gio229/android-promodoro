package com.example.promodoro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "myData.txt";

    private static final String FOLDERNAME = "dataPromodoro";

    private RecyclerView tafRecyclerView;
    private TafAdapter tafAdapter;
    private FloatingActionButton addButton;
    private TextView titre;

    private List<TafModel> tafList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //tafList = new ArrayList<>(); 
        titre = findViewById(R.id.tafText);


        addButton = findViewById(R.id.toAdd);

        JsonDataMaker dataManager = new JsonDataMaker(this);

        dataManager.writeJson("[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"tafName\": \"Examen blanc du néant\",\n" +
                "    \"status\": 0,\n" +
                "    \"tasks\": [\n" +
                "      {\n" +
                "        \"id\": 1,\n" +
                "        \"taskName\": \"Autres cours\",\n" +
                "        \"status\": 0,\n" +
                "        \"taskTime\": \"2h\",\n" +
                "        \"taskTimeSpent\": \"1h30\",\n" +
                "        \"taskTimeLeft\": \"30min\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "]");
        tafList = dataManager.getJsonData();




        tafRecyclerView = findViewById(R.id.tafRecyclerView);
        tafRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tafAdapter = new TafAdapter(this);
        tafRecyclerView.setAdapter(tafAdapter);

        addButton = findViewById(R.id.toAdd);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //titre.setText(dataManager.getTextFromStorage());

    }

    // --------------------

// ACTIONS

// --------------------

 /*   private void save(String data){

            this.writeOnInternalStorage(data);

    }

// ----------------------------------

// UTILS - STORAGE

// ----------------------------------

    private String readFromStorage(){



// 2 - Read from internal storage

// INTERNAL

            File directory;

// Cache

                //directory = getCacheDir();


// Normal

                directory = getFilesDir();


            return JsonDataMaker.getTextFromStorage(directory, this, FILENAME, FOLDERNAME);



    }

// 1 - Write on internal storage

    private void writeOnInternalStorage(String data) {

        File directory;


            //directory = getCacheDir();



            directory = getFilesDir();


        JsonDataMaker.setTextInStorage(directory, this, FILENAME, FOLDERNAME, data);

    }*/

}