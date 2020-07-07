package com.example.fragmentsdrawer.adapters;

import android.content.Context;
import android.graphics.BlendMode;
import android.graphics.PorterDuff;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentsdrawer.R;
import com.example.fragmentsdrawer.databinding.SolutionPartsCardBinding;
import com.example.fragmentsdrawer.databinding.SolutionPartsCardDetailedBinding;
import com.example.fragmentsdrawer.rooms.Equation;

import java.util.ArrayList;

public class SolutionPartsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Pair<String, String>> parts;
    private int focusedPosition = -1;

    private final static byte CLICKED = 0;
    private final static byte REST = 1;
    private final static byte MANUAL = 2;
    private final static byte RESULT = 3;

    public SolutionPartsAdapter(Context context, ArrayList<Pair<String, String>> parts) {
        this.inflater = LayoutInflater.from(context);
        this.parts = parts;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup container, int type) {
        switch (type) {
            case CLICKED:
                SolutionPartsCardDetailedBinding detailedBinding = DataBindingUtil.inflate(inflater, R.layout.solution_parts_card_detailed, container, false);
                return new SolutionClickedViewHolder(detailedBinding, this);
            case REST:
            default:
                SolutionPartsCardBinding binding = DataBindingUtil.inflate(inflater, R.layout.solution_parts_card, container, false);
                return new SolutionViewHolder(binding, this);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        if (parts != null) {
            switch (holder.getItemViewType()) {
                case REST:
                    ((SolutionViewHolder) holder).binding.partChangedString.setText(parts.get(index).second);
                    if (focusedPosition != -1)
                        ((SolutionViewHolder) holder).binding.getRoot().setForegroundTintBlendMode(BlendMode.DARKEN);
                    break;
                case CLICKED:
                    ((SolutionClickedViewHolder) holder).binding.partChangedString.setText(parts.get(index).second);
                    ((SolutionClickedViewHolder) holder).binding.partManualString.setText(parts.get(index).first);
                    break;
                case MANUAL:
                    // TODO: Implement ViewHolder in manual style
                    break;
                case RESULT:
                    // TODO: Implement ViewHolder in result style
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return parts != null ? parts.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return MANUAL;
        else if (position == getItemCount() - 1)
            return RESULT;
        else if (position == focusedPosition)
            return CLICKED;
        else
            return REST;
    }

    class SolutionViewHolder extends RecyclerView.ViewHolder {

        SolutionPartsCardBinding binding;
        SolutionPartsAdapter adapter;

        public SolutionViewHolder(@NonNull final SolutionPartsCardBinding binding, SolutionPartsAdapter adapter) {
            super(binding.getRoot());
            this.binding = binding;
            this.adapter = adapter;

            // Adding a listener to brief solution part view
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    focusedPosition = focusedPosition == -1 ? getLayoutPosition() : -1;
                    notifyDataSetChanged();
                }
            });
        }

    }

    class SolutionClickedViewHolder extends RecyclerView.ViewHolder {

        SolutionPartsCardDetailedBinding binding;
        SolutionPartsAdapter adapter;

        public SolutionClickedViewHolder(@NonNull final SolutionPartsCardDetailedBinding binding, SolutionPartsAdapter adapter) {
            super(binding.getRoot());
            this.binding = binding;
            this.adapter = adapter;

            // Adding a listener to a detailed description of equation. Pop state equation
            binding.solutionWideAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    focusedPosition = -1;
                    notifyDataSetChanged();
                }
            });
        }

    }

}
