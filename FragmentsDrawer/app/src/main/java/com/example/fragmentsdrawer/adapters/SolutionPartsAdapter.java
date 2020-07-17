package com.example.fragmentsdrawer.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentsdrawer.R;
import com.example.fragmentsdrawer.databinding.SolutionPartsCardAnswerBinding;
import com.example.fragmentsdrawer.databinding.SolutionPartsCardBinding;
import com.example.fragmentsdrawer.databinding.SolutionPartsCardDetailedBinding;
import com.example.fragmentsdrawer.rooms.Equation;
import com.example.fragmentsdrawer.util.Step;

import java.util.ArrayList;

public class SolutionPartsAdapter extends RecyclerView.Adapter<SolutionPartsAdapter.SolutionViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Step> parts;
    private String manualFunction, reducedFunction, sharedName;
    private View tint;
    private int focusedPosition = -1;

    private final static byte CLICKED = 0;
    private final static byte REST = 1;
    private final static byte MANUAL = 2;
    private final static byte RESULT = 3;

    public SolutionPartsAdapter(Context context, Equation equation, View tint, String sharedName) {
        this.inflater = LayoutInflater.from(context);
        this.parts = equation.getSteps();
        this.manualFunction = equation.getFunction();
        this.reducedFunction = equation.getReducedFunction();
        this.sharedName = sharedName;
        this.tint = tint;
    }

    @NonNull
    @Override
    public SolutionViewHolder onCreateViewHolder(@NonNull ViewGroup container, int type) {
        SolutionPartsCardDetailedBinding binding = DataBindingUtil.inflate(inflater, R.layout.solution_parts_card_detailed, container, false);
        SolutionViewHolder holder = new SolutionViewHolder(binding, type, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SolutionViewHolder holder, int index) {
        holder.bind(index);
    }

    public void setFocusedPosition(int position) {
        focusedPosition = position >= -1 && parts != null && position < parts.size() ? position : focusedPosition;
    }

    public int getFocusedPosition() {
        return focusedPosition;
    }

    @Override
    public int getItemCount() {
        return parts != null ? parts.size() + 2 : 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return MANUAL;
        else if (position == getItemCount() - 1)
            return RESULT;
        else
            return REST;
    }

    class SolutionViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        SolutionPartsCardDetailedBinding binding;
        SolutionPartsAdapter adapter;
        int type;
        MutableLiveData<Boolean> isDetailed = new MutableLiveData<>();

        SolutionViewHolder(@NonNull final SolutionPartsCardDetailedBinding binding, final int type, final SolutionPartsAdapter adapter) {
            super(binding.getRoot());
            this.binding = binding;
            this.adapter = adapter;
            this.type = type;
            this.context = binding.getRoot().getContext();

            isDetailed.setValue(false);

            binding.setIsDetailed(isDetailed);
            binding.setIsResult(type == RESULT);

            // Adding a listener to a detailed description of equation. Pop state equation
            binding.solutionWideAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    focusedPosition = -1;
                    notifyItemChanged(getLayoutPosition());
                    /* TODO: Add animation */
                    // adapter.tint.setVisibility(View.GONE);
                }
            });

            binding.solutionBriefAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (type != RESULT) {
                        focusedPosition = getLayoutPosition();
                        notifyItemChanged(getLayoutPosition());
                        /* TODO: Add animation */
                        // adapter.tint.setVisibility(View.VISIBLE);
                    }
                }
            });

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (type != RESULT) {
                        focusedPosition = focusedPosition == -1 ? getLayoutPosition() : -1;
                        notifyItemChanged(getLayoutPosition());
                        // adapter.tint.setVisibility(focusedPosition == getLayoutPosition() ? View.VISIBLE : View.GONE);
                    }
                }
            });

            if (getLayoutPosition() == 0)
                binding.partChangedString.setTransitionName(sharedName);
        }

        public void bind(int index) {
            isDetailed.setValue(focusedPosition == index);

            switch (type) {
                case REST:
                    binding.setManual(parts.get(index - 1).step.first);
                    binding.setChanged(parts.get(index - 1).step.second);

                    Step part = parts.get(index - 1);

                    binding.setDescription(context.getResources().getString(R.string.solution_wide_description));
                    binding.setChangeType(context.getResources().getString(R.string.solution_type_of_change_rest_brackets));

                    SpannableString manual = new SpannableString(part.step.first);
                    manual.setSpan(
                            new ForegroundColorSpan(context.getResources().getColor(R.color.secondaryColor)),
                            part.manual.a,
                            part.manual.b,
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    );

                    SpannableString changed = new SpannableString(part.step.second);
                    changed.setSpan(
                            new ForegroundColorSpan(context.getResources().getColor(R.color.secondaryColor)),
                            part.changed.a,
                            part.changed.b,
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    );

                    binding.partChangedString.setText(changed);
                    binding.partManualString.setText(manual);

                    break;
                case MANUAL:
                    binding.setChanged(manualFunction);
                    binding.setManual(context.getResources().getString(R.string.solution_manual_description));

                    binding.partManualString.setTextColor(context.getResources().getColor(R.color.secondaryColor));
                    binding.partManualString.setTypeface(binding.partChangedString.getTypeface(), Typeface.BOLD);

                    binding.setChangeType(context.getResources().getString(R.string.solution_type_of_change_manual));

                    break;
                case RESULT:
                    binding.setChanged(reducedFunction);
                    binding.setChangeType(context.getResources().getString(R.string.solution_type_of_change_result));

                    break;
            }
        }

    }

}
