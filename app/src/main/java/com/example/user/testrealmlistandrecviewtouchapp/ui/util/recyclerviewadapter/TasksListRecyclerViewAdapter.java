package com.example.user.testrealmlistandrecviewtouchapp.ui.util.recyclerviewadapter;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.testrealmlistandrecviewtouchapp.R;
import com.example.user.testrealmlistandrecviewtouchapp.ui.view.util.TasksListRecyclerViewGetter;

import java.util.ArrayList;

public class TasksListRecyclerViewAdapter
        extends RecyclerView.Adapter<TasksListRecyclerViewAdapter.ViewHolder> {

    ArrayList<String> arrayList;
    TasksListRecyclerViewGetter tasksListRecyclerViewGetter;


    public TasksListRecyclerViewAdapter(ArrayList<String> arrayList, AppCompatActivity activity){
        this.arrayList = arrayList;
        //activity as context
        tasksListRecyclerViewGetter = new TasksListRecyclerViewGetter(activity);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_text);
        }
    }
}
