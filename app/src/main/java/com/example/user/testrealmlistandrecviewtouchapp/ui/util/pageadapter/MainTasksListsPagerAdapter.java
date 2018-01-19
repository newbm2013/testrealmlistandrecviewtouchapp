package com.example.user.testrealmlistandrecviewtouchapp.ui.util.pageadapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.testrealmlistandrecviewtouchapp.data.local.simpleArrayListModel.ArrayListController;
import com.example.user.testrealmlistandrecviewtouchapp.ui.view.util.TasksListRecyclerViewGetter;


public class MainTasksListsPagerAdapter extends PagerAdapter {

    AppCompatActivity actvity;
    TasksListRecyclerViewGetter tasksListRecyclerViewGetter;
    ArrayListController arrayListController;

    public MainTasksListsPagerAdapter(AppCompatActivity actvity){
        this.actvity = actvity;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        tasksListRecyclerViewGetter = new TasksListRecyclerViewGetter(actvity);
        arrayListController = new ArrayListController();
        return tasksListRecyclerViewGetter.getRecyclerView(container, arrayListController.getArrayList(position));
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
