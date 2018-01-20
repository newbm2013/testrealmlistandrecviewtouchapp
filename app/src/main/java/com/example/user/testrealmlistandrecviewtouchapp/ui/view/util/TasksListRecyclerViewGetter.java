package com.example.user.testrealmlistandrecviewtouchapp.ui.view.util;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.testrealmlistandrecviewtouchapp.R;
import com.example.user.testrealmlistandrecviewtouchapp.ui.util.recyclerviewadapter.TasksListRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class TasksListRecyclerViewGetter {

    AppCompatActivity activity;
    LayoutInflater layoutInflater;
    RecyclerView tasksListRecyclerView;
    TasksListRecyclerViewAdapter tasksListRecyclerViewAdapter;
    ArrayList arrayList;
    Object arrayListCopy;


    public TasksListRecyclerViewGetter(AppCompatActivity activity){
        this.activity = activity;
    }

    public RecyclerView getRecyclerView(ViewGroup viewGroup, ArrayList<String> arrayList ){
        this.arrayList = arrayList;
        layoutInflater = activity.getLayoutInflater();
        tasksListRecyclerView = (RecyclerView) layoutInflater.inflate(R.layout.tasks_list_recycler_view, viewGroup, false);
        tasksListRecyclerViewAdapter = new TasksListRecyclerViewAdapter(arrayList, activity);
        tasksListRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        tasksListRecyclerView.setAdapter(tasksListRecyclerViewAdapter);
        arrayListCopy=arrayList.clone();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(tasksListRecyclerView);
        return tasksListRecyclerView;
    }




    @SuppressWarnings("all")
    private ItemTouchHelper.Callback createHelperCallback() {

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP| ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT,
                0){

            int dragFrom = -1;
            int dragTo = -1;


            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {


                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();

                if(dragFrom == -1) {dragFrom =  fromPosition;}
                dragTo = toPosition;

                tasksListRecyclerViewAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            private void reallyMoved(int from, int to) {arrayList.add(to, arrayList.remove(from));}

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);

                if(dragFrom != -1 && dragTo != -1 && dragFrom != dragTo) {
                    reallyMoved(dragFrom, dragTo);
                    if (!arrayList.equals(arrayListCopy)){
                        Log.d("DTAG", "from index " + dragFrom + " ,to index " + dragTo);
                        Log.d("DTAG", "OLD " + Arrays.toString(((ArrayList)arrayListCopy).toArray()));
                        Log.d("DTAG", "NEW " + Arrays.toString(arrayList.toArray()));
                        arrayListCopy=arrayList.clone();
                    }
                }
                dragFrom = dragTo = -1;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
            }
        };



        return simpleItemTouchCallback;
    }
}
