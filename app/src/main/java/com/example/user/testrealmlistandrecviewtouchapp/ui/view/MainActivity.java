package com.example.user.testrealmlistandrecviewtouchapp.ui.view;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.testrealmlistandrecviewtouchapp.R;
import com.example.user.testrealmlistandrecviewtouchapp.ui.util.pageadapter.MainTasksListsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    MainTasksListsPagerAdapter mainTasksListsPagerAdapter;
    ViewPager mainTasksListsViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTasksListsViewPager = findViewById(R.id.main_tasks_lists_view_pager);
        mainTasksListsPagerAdapter = new MainTasksListsPagerAdapter(this);
        mainTasksListsViewPager.setAdapter(mainTasksListsPagerAdapter);
    }
}
