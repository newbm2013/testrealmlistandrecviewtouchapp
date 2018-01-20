package com.example.user.testrealmlistandrecviewtouchapp.ui.util.recyclerviewadapter;


import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.testrealmlistandrecviewtouchapp.R;
import com.example.user.testrealmlistandrecviewtouchapp.ui.view.util.TasksListRecyclerViewGetter;

import java.util.ArrayList;


public class TasksListRecyclerViewAdapter
        extends RecyclerView.Adapter<TasksListRecyclerViewAdapter.ViewHolder> {

    ArrayList<String> arrayList;
    boolean addithionalItemViewPanelShowing = false;
    View viewWithShowingAdditionalItemViewPanel;
    int normalHeight;
    View.OnClickListener onClickListener;
    int clickCount = 0;



    Handler handler;
    boolean handlerRun;
    Runnable runnable;


    public TasksListRecyclerViewAdapter(ArrayList<String> arrayList, AppCompatActivity activity) {
        this.arrayList = arrayList;
        handler = new Handler();



        //activity as context
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(final View view) {


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        clickCount++;
                    }
                }).start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (!handlerRun){
                            handlerRun = true;
                            handler.postDelayed(runnable, 170);
                        }
                    }
                }).start();

                runnable = new Runnable() {
                    @Override
                    public void run() {


                        if (clickCount == 1) {
                            Log.d("DTAG", "onClick: 1");
                        }

                        if (clickCount == 2) {
                            if (!addithionalItemViewPanelShowing) {
                                //                    normalHeight = view.getHeight();
                                //                    view.setMinimumHeight(normalHeight*2);
                                view.findViewById(R.id.ll_additional_item_view_panel).setVisibility(View.VISIBLE);
                                addithionalItemViewPanelShowing = true;
                                viewWithShowingAdditionalItemViewPanel = view;
                            } else if (addithionalItemViewPanelShowing) {
                                //                    viewWithShowingAdditionalItemViewPanel.setMinimumHeight(normalHeight);
                                viewWithShowingAdditionalItemViewPanel
                                        .findViewById(R.id.ll_additional_item_view_panel).setVisibility(View.GONE);
                                if (!view.equals(viewWithShowingAdditionalItemViewPanel)) {
                                    //                        view.setMinimumHeight(normalHeight*2);
                                    view.findViewById(R.id.ll_additional_item_view_panel).setVisibility(View.VISIBLE);
                                    addithionalItemViewPanelShowing = true;
                                    viewWithShowingAdditionalItemViewPanel = view;
                                    clickCount=0;
                                    handlerRun = false;
                                    return;
                                }
                                addithionalItemViewPanelShowing = false;
                            }
                        }


                        clickCount=0;
                        handlerRun = false;
                    }
                };





            }
        };
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        view.setOnClickListener(onClickListener);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText("" + arrayList.get(position));
        holder.llAdditionalPanel.setVisibility(View.GONE);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        LinearLayout llAdditionalPanel;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_text);
            llAdditionalPanel = itemView.findViewById(R.id.ll_additional_item_view_panel);
        }
    }
}
