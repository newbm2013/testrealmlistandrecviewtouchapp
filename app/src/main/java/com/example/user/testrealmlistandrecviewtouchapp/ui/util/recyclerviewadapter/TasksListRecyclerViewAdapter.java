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
import com.example.user.testrealmlistandrecviewtouchapp.R;
import java.util.ArrayList;


public class TasksListRecyclerViewAdapter
        extends RecyclerView.Adapter<TasksListRecyclerViewAdapter.ViewHolder> {

    ArrayList<String> arrayList;
    boolean addithionalItemViewPanelShowing = false;
    View viewWithShowingAdditionalItemViewPanel;
    View.OnLongClickListener onLongClickListener;


    public TasksListRecyclerViewAdapter(ArrayList<String> arrayList, AppCompatActivity activity) {
        this.arrayList = arrayList;

        onLongClickListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {

                if (!addithionalItemViewPanelShowing) {
                    view.findViewById(R.id.ll_additional_item_view_panel).setVisibility(View.VISIBLE);
                    addithionalItemViewPanelShowing = true;
                    viewWithShowingAdditionalItemViewPanel = view;
                } else if (addithionalItemViewPanelShowing) {
                    viewWithShowingAdditionalItemViewPanel
                            .findViewById(R.id.ll_additional_item_view_panel).setVisibility(View.GONE);
                    if (!view.equals(viewWithShowingAdditionalItemViewPanel)) {
                        view.findViewById(R.id.ll_additional_item_view_panel).setVisibility(View.VISIBLE);
                        addithionalItemViewPanelShowing = true;
                        viewWithShowingAdditionalItemViewPanel = view;
                        return false;
                    }
                    addithionalItemViewPanelShowing = false;
                }
                return false;
            }
        };
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        view.setOnLongClickListener(onLongClickListener);
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
