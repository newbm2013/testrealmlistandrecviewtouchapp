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
import com.example.user.testrealmlistandrecviewtouchapp.data.local.simpleArrayListModel.RList;
import com.example.user.testrealmlistandrecviewtouchapp.data.local.simpleArrayListModel.RListsContainer;
import com.example.user.testrealmlistandrecviewtouchapp.data.local.simpleArrayListModel.RTask;
import com.example.user.testrealmlistandrecviewtouchapp.ui.util.recyclerviewadapter.TasksListRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import io.realm.Realm;

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

            Realm realm = Realm.getDefaultInstance();
            RListsContainer rListsContainer = realm.where(RListsContainer.class).findFirst();
            RList<RTask> rList = (RList<RTask>) rListsContainer.realmListContainer.get(0);

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

            private void reallyMoved(final int from, final int to) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        rList.tasks.add(to, rList.tasks.remove(from));
                    }
                });
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);



                if(dragFrom != -1 && dragTo != -1 && dragFrom != dragTo) {
                    long time = System.currentTimeMillis();
                    reallyMoved(dragFrom, dragTo);
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
