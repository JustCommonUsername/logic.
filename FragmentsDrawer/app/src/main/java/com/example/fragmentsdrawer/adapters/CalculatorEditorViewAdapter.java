package com.example.fragmentsdrawer.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentsdrawer.R;
import com.example.fragmentsdrawer.core.IllegalLogicEquationException;
import com.example.fragmentsdrawer.models.CalculatorViewModel;

import java.util.ArrayList;
import java.util.List;

public class CalculatorEditorViewAdapter extends RecyclerView.Adapter<CalculatorEditorViewAdapter.EditorViewHolder> {

    private LayoutInflater inflater;
    private List<Character> mValuesList;
    private CalculatorViewModel viewModel;
    private int addedChildIndex;
    private LifecycleOwner owner;
    private final MutableLiveData<Integer> pendingFocusIndex = new MutableLiveData<>();

    public CalculatorEditorViewAdapter(Context context, List<Character> mValuesList,
                                       CalculatorViewModel viewModel, LifecycleOwner lifecycleOwner) {
        this.inflater = LayoutInflater.from(context);
        this.mValuesList = mValuesList;
        this.viewModel = viewModel;
        this.owner = lifecycleOwner;

        if (mValuesList.size() == 0)
            mValuesList.add('\0');
    }

    @NonNull
    @Override
    public EditorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View editorItem = inflater.inflate(R.layout.calculator_edit_text, parent, false);
        return new EditorViewHolder(editorItem, this);
    }

    @Override
    public void onBindViewHolder(@NonNull EditorViewHolder holder, int position) {
        if (mValuesList != null) {
            holder.field.setText(Character.toString(mValuesList.get(position)));

            if (addedChildIndex == position)
                holder.field.requestFocus();
        }
    }

    @Override
    public int getItemCount() {
        if (mValuesList != null)
            return mValuesList.size();
        return 0;
    }

    public void add(char value, int focusedIndex) {
        mValuesList.add(focusedIndex + 1, value);
        addedChildIndex = focusedIndex + 1;

        if (value == '(') {
            mValuesList.add(focusedIndex + 2,')');
        }

        notifyDataSetChanged();
    }

    public void delete(char value, int index) {
        if (index > 0 && index < mValuesList.size()) {
            mValuesList.remove(index);
            notifyItemRemoved(index);

            int toFocus = --index;

            if (value == ')')
            {
                do {
                    index = mValuesList.get(index) != ')' ? --index : -1;
                } while (index > 0 && mValuesList.get(index) != '(');

                if (index >= 0) {
                    mValuesList.remove(index);
                    notifyItemRemoved(index);
                }
            }
            else if (value == '(')
            {
                do {
                    try {
                        index = mValuesList.get(++index) != '(' ? index : mValuesList.size();
                    } catch (IndexOutOfBoundsException e) {
                        index = mValuesList.size();
                    }
                } while (index < mValuesList.size() && mValuesList.get(index) != ')');

                if (index < mValuesList.size()) {
                    mValuesList.remove(index);
                    notifyItemRemoved(index);
                }
            }

            pendingFocusIndex.setValue(toFocus);
        }
    }

    public String fields() {
        StringBuffer result = new StringBuffer();
        for (char character: mValuesList) {
            if (character != '\0')
                result.append(character);
        }
        return result.toString();
    }

    public void onResume(ArrayList<Character> input) {
        mValuesList = input;
        notifyDataSetChanged();
    }

    public void changePosition(int index) {
        if (index >= 0 && index < mValuesList.size())
            pendingFocusIndex.setValue(index);
    }

    public List<Character> getValuesList() {
        return mValuesList;
    }

    class EditorViewHolder extends RecyclerView.ViewHolder {

        final EditText field;
        final CalculatorEditorViewAdapter adapter;

        EditorViewHolder(@NonNull View view, CalculatorEditorViewAdapter adapter) {
            super(view);
            this.adapter = adapter;
            this.field = view.findViewById(R.id.calculator_field);

            adapter.pendingFocusIndex.observe(owner, new Observer<Integer>() {
                @Override
                public void onChanged(Integer index) {
                    if (index == getLayoutPosition())
                        field.requestFocus();
                }
            });
        }

    }

}
