package com.example.promodoro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.promodoro.Adapter.TafAdapter;
import com.example.promodoro.Adapter.TaskAdapter;
import com.example.promodoro.Model.JsonDataMaker;
import com.example.promodoro.Model.TafModel;
import com.example.promodoro.Model.TaskModel;

import java.util.ArrayList;
import java.util.List;

public class TafDetailsActivity extends AppCompatActivity {

    private RecyclerView taskRecyclerView;
    private TaskAdapter taskAdapter;
    private List<TaskModel> taskList;
    private TextView activityName;
    private TafModel myActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taf_details);

        getSupportActionBar().hide();

        taskList = new ArrayList<>();

        JsonDataMaker dataManager = new JsonDataMaker(this);
        myActivity = dataManager.getJsonOneActivity(Integer.parseInt(this.getIntent().getExtras().getString("id")));
        taskList = myActivity.getTasks();


        activityName = findViewById(R.id.tafDetailsText);
        activityName.setText(myActivity.getTafName());

        taskRecyclerView = findViewById(R.id.tafDetailsRecyclerView);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter(this);
        taskRecyclerView.setAdapter(taskAdapter);

        /*TaskModel task = new TaskModel();
        task.setTaskName("Ceci est ma première tâche");
        task.setTaskTime("2h");
        task.setTaskTimeSpent("00min");
        task.setTaskTimeLeft("00min");
        task.setId(1);
        task.setStatus(0);


        taskList.add(task);
        taskList.add(task);

        taskAdapter.setTask(taskList);*/
    }
}