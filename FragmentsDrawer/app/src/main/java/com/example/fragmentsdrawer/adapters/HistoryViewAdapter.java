package com.example.fragmentsdrawer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentsdrawer.R;
import com.example.fragmentsdrawer.models.CalculatorViewModel;
import com.example.fragmentsdrawer.rooms.Equation;

import java.util.List;

public class HistoryViewAdapter extends RecyclerView.Adapter<HistoryViewAdapter.HistoryViewHolder> {

    private LayoutInflater inflater;
    private List<Equation> mEquationList;
    private NavController controller;
    private CalculatorViewModel viewModel;

    public HistoryViewAdapter(Context context, List<Equation> mEquationList, NavController navController, CalculatorViewModel viewModel) {
        inflater = LayoutInflater.from(context);
        this.mEquationList = mEquationList;
        controller = navController;
        this.viewModel = viewModel;
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
                    // ViewModel changes its current equation in order to perform query
                    viewModel.getCurrentEquation().setValue(mCurrentEquation.getFunction());
                    // DEBUG
                    Toast.makeText(itemView.getContext(), mCurrentEquation.getFunction(), Toast.LENGTH_SHORT).show();
                    // Navigating to solutions fragment
                    controller.navigate(R.id.nav_home_solution);
                }
            });
        }
    }

}
