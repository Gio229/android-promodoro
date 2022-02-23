package com.example.promodoro.Model;

import java.util.ArrayList;
import java.util.Arrays;

public class TafModel {
    private int id, status;
    private String tafName;
    /*private TaskModel[] tableauSimple = new TaskModel[]{};
    private ArrayList tableauDynamique = new ArrayList(Arrays.asList(tableauSimple));*/
    private ArrayList<TaskModel> tasks = new ArrayList<TaskModel>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTafName() {
        return tafName;
    }

    public void setTafName(String tafName) {
        this.tafName = tafName;
    }

    public ArrayList<TaskModel> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<TaskModel> tasks) {
        this.tasks = tasks;
    }
}
