package com.example.fragmentsdrawer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentsdrawer.R;
import com.example.fragmentsdrawer.core.IllegalLogicEquationException;
import com.example.fragmentsdrawer.models.CalculatorViewModel;

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
        viewModel.getCurrentEquation().setValue(null);
        mValuesList.add(focusedIndex + 1, value);
        addedChildIndex = focusedIndex + 1;

        if (value == '(') {
            mValuesList.add(focusedIndex + 2,')');
        }

        notifyDataSetChanged();
    }

    public void delete(char value, int index) throws IllegalLogicEquationException {
        mValuesList.remove(index);

        if (value == ')') {
            for (int i = index; i >= 0; i--) {
                if (mValuesList.get(i) == '(')
                    mValuesList.remove(i);
                else if (i == 0)
                    throw new IllegalLogicEquationException();
            }
        }
        else if (value == '(') {
            for (int i = index; i < mValuesList.size(); i++) {
                if (mValuesList.get(i) == ')')
                    mValuesList.remove(i);
                else if (i + 1 == mValuesList.size())
                    throw new IllegalLogicEquationException();
            }
        }

        notifyDataSetChanged();
    }

    public String fields() {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < mValuesList.size(); i++) {
            result.append(Character.toString(mValuesList.get(i)));
        }
        return result.toString();
    }

    public void onResume(String input) {
        if (mValuesList != null) {
            if (input != null)
                for (char ch : input.toCharArray())
                    mValuesList.add(ch);
            else
                mValuesList.add(' ');
        }

        notifyDataSetChanged();
    }

    public void changePosition(int index) {
        if (index >= 0 && index < mValuesList.size())
            pendingFocusIndex.setValue(index);
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
                        field.setBackgroundDrawable(inflater.inflate(R.layout.calculator_edit_text, null, false).getBackground());
                    else
                        field.setBackgroundResource(android.R.color.transparent);
                }
            });

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
