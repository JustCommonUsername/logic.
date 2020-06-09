package com.example.fragmentsdrawer.adapters;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentsdrawer.R;

import java.util.LinkedList;
import java.util.List;

public class CalculatorEditorViewAdapter extends RecyclerView.Adapter<CalculatorEditorViewAdapter.EditorViewHolder> {

    private LayoutInflater inflater;
    private List<Character> mValuesList;
    private LinkedList<Pair<Integer, Float>> mBracketsIDtoSize = new LinkedList<>();

    public CalculatorEditorViewAdapter(Context context, List<Character> mValuesList) {
        inflater = LayoutInflater.from(context);
        this.mValuesList = mValuesList;
    }

    @NonNull
    @Override
    public EditorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View editorItem = inflater.inflate(R.layout.calculator_edit_text, parent, false);
        return new EditorViewHolder(editorItem, this);
    }

    @Override
    public void onBindViewHolder(@NonNull EditorViewHolder holder, int position) {
        if (mValuesList != null)
            if (mValuesList.get(position) == '(' || mValuesList.get(position) == ')')
                mBracketsIDtoSize.push(
                        new Pair<>(
                        holder.field.getId(),
                        holder.field.getTextSize()));
        else
            Toast.makeText(inflater.getContext(), "No information yet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        if (mValuesList != null)
            return mValuesList.size();
        return 0;
    }

    class EditorViewHolder extends RecyclerView.ViewHolder {

        final EditText field;
        final CalculatorEditorViewAdapter adapter;

        EditorViewHolder(@NonNull View view, CalculatorEditorViewAdapter adapter) {
            super(view);
            this.adapter = adapter;
            this.field = view.findViewById(R.id.calculator_field);

            field.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus)
                        field.setBackgroundResource(android.R.drawable.edit_text);
                    else
                        field.setBackgroundResource(0);
                }
            });
        }

    }

}
