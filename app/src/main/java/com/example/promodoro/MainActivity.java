package com.example.promodoro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.promodoro.Adapter.TafAdapter;
import com.example.promodoro.Model.TafModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView tafRecyclerView;
    private TafAdapter tafAdapter;

    private List<TafModel> tafList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        tafList = new ArrayList<>();


        tafRecyclerView = findViewById(R.id.tafRecyclerView);
        tafRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tafAdapter = new TafAdapter(this);
        tafRecyclerView.setAdapter(tafAdapter);

        TafModel taf = new TafModel();
        taf.setTafName("Ceci est ma première activité");
        taf.setId(1);
        taf.setStatus(0);


        tafList.add(taf);
        tafList.add(taf);
        tafList.add(taf);
        tafList.add(taf);

        tafAdapter.setTaf(tafList);

    }
}