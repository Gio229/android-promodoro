package com.example.promodoro.Model;

import java.util.ArrayList;

public class TaskModel {
    private int id, status;
    private String taskName, taskTime, taskTimeSpent, taskTimeLeft;

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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public String getTaskTimeSpent() {
        return taskTimeSpent;
    }

    public void setTaskTimeSpent(String taskTimeSpent) {
        this.taskTimeSpent = taskTimeSpent;
    }

    public String getTaskTimeLeft() {
        return taskTimeLeft;
    }

    public void setTaskTimeLeft(String taskTimeLeft) {
        this.taskTimeLeft = taskTimeLeft;
    }
}
