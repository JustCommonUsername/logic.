package com.example.fragmentsdrawer.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentsdrawer.R;
import com.example.fragmentsdrawer.models.CalculatorViewModel;
import com.example.fragmentsdrawer.rooms.Equation;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class HistoryViewAdapter extends RecyclerView.Adapter<HistoryViewAdapter.HistoryViewHolder> {

    private @NonNull LayoutInflater inflater;
    private List<Equation> mEquationList;
    private NavController controller;
    private CalculatorViewModel viewModel;
    private RelativeLayout historyViewContainer;

    private int recentlyDeletedIndex;
    private Equation recentlyDeletedEquation;

    public HistoryViewAdapter(Context context, List<Equation> mEquationList, NavController navController, CalculatorViewModel viewModel, RelativeLayout historyViewContainer) {
        inflater = LayoutInflater.from(context);
        this.mEquationList = mEquationList;
        controller = navController;
        this.viewModel = viewModel;
        this.historyViewContainer = historyViewContainer;
    }

    @NonNull
    public LayoutInflater getInflater() {
        return inflater;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View historyItem = inflater.inflate(R.layout.history_item_card, parent, false);
        return new HistoryViewHolder(historyItem, this);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        if (mEquationList != null) {
            Equation current = mEquationList.get(position);
            holder.itemEquationName.setText(current.getFunction());
            holder.itemChangeType.setText(current.getHowChanged());
        }
        else {
            holder.itemEquationName.setText("No Equation");
            holder.itemChangeType.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if (mEquationList != null)
            return mEquationList.size();
        return 0;
    }

    public void deleteItem(int index) {
        recentlyDeletedIndex = index;
        recentlyDeletedEquation = mEquationList.get(recentlyDeletedIndex);

        mEquationList.remove(index);
        notifyItemRemoved(index);

        Snackbar
                .make(historyViewContainer, R.string.home_history_snackbar_question_text, Snackbar.LENGTH_SHORT)
                .addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        if (event != Snackbar.Callback.DISMISS_EVENT_ACTION)
                            viewModel.delete(recentlyDeletedEquation);
                    }
                })
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        resetItem(recentlyDeletedIndex, recentlyDeletedEquation);
                    }
                })
                .setTextColor(inflater.getContext().getResources().getColor(R.color.primaryColor))
                .setActionTextColor(inflater.getContext().getResources().getColor(R.color.secondaryColor))
                .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
                .show();
    }

    public void resetItem(int index, Equation item) {
        mEquationList.add(index, item);
        notifyItemInserted(index);
    }

    public void setEquations(List<Equation> words) {
        mEquationList = words;
        notifyDataSetChanged();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {

        private final CardView historyItemView;
        final TextView itemEquationName;
        final TextView itemChangeType;
        final HistoryViewAdapter mAdapter;

        public HistoryViewHolder(@NonNull final View itemView, HistoryViewAdapter adapter) {
            super(itemView);
            historyItemView = itemView.findViewById(R.id.history_item_container);
            mAdapter = adapter;

            itemEquationName = historyItemView.findViewById(R.id.history_item_equation);
            itemChangeType = historyItemView.findViewById(R.id.history_item_change_type);

            historyItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Getting position of a click
                    int position = getLayoutPosition();
                    // Getting current value from data set
                    Equation mCurrentEquation = mEquationList.get(position);
                    // ViewModel changes one of its values to perform showing the results
                    viewModel.setSolvedEquation(mCurrentEquation);
                    // Navigating to solutions fragment
                    controller.navigate(R.id.action_nav_home_to_nav_home_solution);
                }
            });
        }
    }

}
