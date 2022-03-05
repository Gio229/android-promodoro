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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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
        tafList = dataManager.getJsonData();




        tafRecyclerView = findViewById(R.id.tafRecyclerView);
        tafRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tafAdapter = new TafAdapter(this);
        tafRecyclerView.setAdapter(tafAdapter);

        /*TafModel taf = new TafModel();
        taf.setTafName("Ceci est ma première activité");
        taf.setId(1);
        taf.setStatus(0);

        TafModel taff = new TafModel();
        taff.setTafName("Ceci est ma première activité");
        taff.setId(2);
        taff.setStatus(1);


        tafList.add(taf);
        tafList.add(taff);

        tafAdapter.setTaf(tafList);*/

        //titre.setText(String.valueOf(dataManager.writeJson("")));

        titre.setText(String.valueOf(dataManager.writeJson("[{\n" +
                "    \"id\": 1,\n" +
                "    \"tafName\": \"Un autre\",\n" +
                "    \"status\": 0,\n" +
                "    \"tasks\": [\n" +
                "      {\n" +
                "        \"id\": 1,\n" +
                "        \"taskName\": \"PCT cours\",\n" +
                "        \"status\": 0,\n" +
                "        \"taskTime\": \"2h\",\n" +
                "        \"taskTimeSpent\": \"1h30\",\n" +
                "        \"taskTimeLeft\": \"30min\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 2,\n" +
                "        \"taskName\": \"DTI cours\",\n" +
                "        \"status\": 0,\n" +
                "        \"taskTime\": \"2h\",\n" +
                "        \"taskTimeSpent\": \"1h30\",\n" +
                "        \"taskTimeLeft\": \"30min\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }]")));
        //titre.setText(dataManager.test());

        tafList = dataManager.getJsonData();
        titre.setText(dataManager.test());
    }

}