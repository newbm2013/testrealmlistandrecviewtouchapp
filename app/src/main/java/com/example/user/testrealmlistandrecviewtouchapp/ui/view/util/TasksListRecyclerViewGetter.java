package com.example.user.testrealmlistandrecviewtouchapp.ui.view.util;


import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.testrealmlistandrecviewtouchapp.R;
import com.example.user.testrealmlistandrecviewtouchapp.ui.util.recyclerviewadapter.TasksListRecyclerViewAdapter;

import java.util.ArrayList;

public class TasksListRecyclerViewGetter {


    AppCompatActivity activity;
    LayoutInflater layoutInflater;
    RecyclerView tasksListRecyclerView;
    TasksListRecyclerViewAdapter tasksListRecyclerViewAdapter;
    ArrayList arrayList;
    boolean fingerUp;


    public TasksListRecyclerViewGetter(AppCompatActivity activity){
        this.activity = activity;
    }

    public RecyclerView getRecyclerView(ViewGroup viewGroup, ArrayList<String> arrayList ){
        this.arrayList = arrayList;
        layoutInflater = activity.getLayoutInflater();
        tasksListRecyclerView = (RecyclerView) layoutInflater.inflate(R.layout.tasks_list_recycler_view, viewGroup, false);
//        tasksListRecyclerView = view.findViewById(R.id.rv);
        tasksListRecyclerViewAdapter = new TasksListRecyclerViewAdapter(arrayList, activity);
        tasksListRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        tasksListRecyclerView.setAdapter(tasksListRecyclerViewAdapter);


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(tasksListRecyclerView);


        return tasksListRecyclerView;
    }


    @SuppressWarnings("all")
    private ItemTouchHelper.Callback createHelperCallback() {
        /*First Param is for Up/Down motion, second is for Left/Right.
        Note that we can supply 0, one constant (e.g. ItemTouchHelper.LEFT), or two constants (e.g.
        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) to specify what directions are allowed.
        */



//        final View.OnTouchListener touchListener = new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
//                    fingerUp = true;
//                }
//
//                else if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) fingerUp = false;
//
//                return true;
//            }
//        };

//        tasksListRecyclerView.setOnTouchListener(touchListener);


        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP| ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {




            //not used, as the first parameter above is 0
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {




                recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                    @Override
                    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                        return false;
                    }

                    @Override
                    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

                        if (e.getAction() == MotionEvent.ACTION_UP){
                            fingerUp = true;
                        }

                    }

                    @Override
                    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                    }
                });


                int i = target.getAdapterPosition();
                int i2 = viewHolder.getAdapterPosition();

                if (fingerUp){

                    arrayList.set(i, arrayList.get(i2));
                    tasksListRecyclerViewAdapter.notifyItemMoved(i2, i);
                    String s = "";
                    for (int ii=0; ii<arrayList.size(); ii++ ) s += arrayList.get(ii);
                    Log.d("DTAG", "onMove: " + s);
                }

                tasksListRecyclerViewAdapter.notifyItemMoved(i2, i);






                return true;
            }

            @Override
            public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
//                super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition();
                arrayList.remove(position);

//                tasksListRecyclerViewAdapter.notifyItemRemoved(position);
            }
        };



        return simpleItemTouchCallback;
    }
}


//https://medium.com/@fanfatal/android-swipe-menu-with-recyclerview-8f28a235ff28
//https://stackoverflow.com/questions/35920584/android-how-to-catch-drop-action-of-itemtouchhelper-which-is-used-with-recycle