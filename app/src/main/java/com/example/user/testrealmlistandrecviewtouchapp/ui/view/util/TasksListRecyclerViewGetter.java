package com.example.user.testrealmlistandrecviewtouchapp.ui.view.util;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.user.testrealmlistandrecviewtouchapp.R;
import com.example.user.testrealmlistandrecviewtouchapp.ui.util.recyclerviewadapter.TasksListRecyclerViewAdapter;

import java.util.ArrayList;

public class TasksListRecyclerViewGetter {


    AppCompatActivity activity;
    LayoutInflater layoutInflater;
    RecyclerView tasksListRecyclerView;
    TasksListRecyclerViewAdapter tasksListRecyclerViewAdapter;


    public TasksListRecyclerViewGetter(AppCompatActivity activity){
        this.activity = activity;
    }

    public RecyclerView getRecyclerView(ViewGroup viewGroup, ArrayList<String> arrayList ){
        layoutInflater = activity.getLayoutInflater();
        tasksListRecyclerView = (RecyclerView) layoutInflater.inflate(R.layout.tasks_list_recycler_view, viewGroup, false);
        tasksListRecyclerViewAdapter = new TasksListRecyclerViewAdapter(arrayList, activity);
        tasksListRecyclerView.setAdapter(tasksListRecyclerViewAdapter);
        return tasksListRecyclerView;
    }



}
