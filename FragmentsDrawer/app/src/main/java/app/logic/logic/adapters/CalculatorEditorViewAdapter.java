package app.logic.logic.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import app.logic.logic.R;
import app.logic.logic.models.CalculatorViewModel;

import java.util.ArrayList;
import java.util.List;

public class CalculatorEditorViewAdapter extends RecyclerView.Adapter<CalculatorEditorViewAdapter.EditorViewHolder> {

    private LayoutInflater inflater;
    private List<String> mValuesList;
    private CalculatorViewModel viewModel;
    private int addedChildIndex;
    private LifecycleOwner owner;
    private final MutableLiveData<Integer> pendingFocusIndex = new MutableLiveData<>();

    public static final int FIRST = -1;
    public static final int REST = -2;
    public static final int LAST = -3;

    public CalculatorEditorViewAdapter(Context context, List<String> mValuesList,
                                       CalculatorViewModel viewModel, LifecycleOwner lifecycleOwner) {
        this.inflater = LayoutInflater.from(context);
        this.mValuesList = mValuesList;
        this.viewModel = viewModel;
        this.owner = lifecycleOwner;

        mValuesList.add(null);
    }

    @NonNull
    @Override
    public EditorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View editorItem = inflater.inflate(R.layout.calculator_edit_text, parent, false);
        return new EditorViewHolder(editorItem, this);
    }

    @Override
    public void onBindViewHolder(@NonNull final EditorViewHolder holder, int position) {
        if (mValuesList != null) {
            holder.field.setText(mValuesList.get(position));

            final Resources resources = holder.field.getResources();

            if (addedChildIndex == position)
                holder.field.requestFocus();

            if (getItemViewType(position) == LAST)
                holder.field.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(android.R.color.darker_gray)));

            if (getItemViewType(position) == FIRST) {
                if (getItemCount() == 1 && TextUtils.isEmpty(mValuesList.get(position)))
                    holder.field.setHint(resources.getString(R.string.home_calculator_greeting));
                else
                    holder.field.setHint(null);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mValuesList != null)
            return mValuesList.size();
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return FIRST;
        else if (position == getItemCount() - 1)
            return LAST;
        else
            return REST;
    }

    public void add(String value, int focusedIndex) {
        mValuesList.add(focusedIndex + 1, value);

        int addedBracketsIndex;

        if (value != null)
            addedChildIndex = (addedBracketsIndex = addBracketsValue(value, focusedIndex + 1)) == focusedIndex
                            ? focusedIndex + 1
                            : addedBracketsIndex;
        else
            addedChildIndex = focusedIndex + 1;

        notifyDataSetChanged();
    }

    public void delete(String value, int index) {
        if (index >= 0 && index < mValuesList.size()) {
            mValuesList.remove(index);
            notifyItemRemoved(index);

            int toFocus;

            if (value.equals("(") || value.equals(")")) {
                toFocus = index;
                final boolean change;

                while (true) {
                    if (isStop(index += value.equals("(") ? 1 : -1))
                        break;
                }

                if (change = index >= 0 && index < mValuesList.size()) {
                    if (mValuesList.get(index) != null &&
                            !mValuesList.get(index).equals(value)) {
                        if (index > 0) {
                            mValuesList.remove(index);
                            notifyItemRemoved(index);
                        }
                        else
                            set(null, index);
                    }
                }

                toFocus -= change ? 2 : 1;
            } else
                toFocus = index - 1;

            pendingFocusIndex.setValue(toFocus);
        }
    }

    public void delete(int index) {
        final String value = mValuesList.get(index);

        mValuesList.remove(index);
        notifyItemRemoved(index);

        pendingFocusIndex.setValue(value == null ? index : index - 1);
    }

    public void set(String value, int index) {
        mValuesList.set(index, value);

        if (value != null) {
            addedChildIndex = addBracketsValue(value, index);
            notifyDataSetChanged();
        } else
            notifyItemChanged(index);
    }

    public String get(int index) {
        return mValuesList.get(index);
    }

    public int getNullIndex(int index) {
        if (index >= getItemCount() - 1 || mValuesList.get(index) != null)
            return -1;
        else {
            return mValuesList.get(index) == null ? index :
                    mValuesList.get(index + 1) == null ? index + 1 : -1;
        }
    }

    private int addBracketsValue(String value, int focusedIndex) {
        if (value.equals("(")) {
            boolean isBracket;

            try {
                isBracket = mValuesList.get(focusedIndex + 1).equals("(")
                        || mValuesList.get(focusedIndex + 1).equals(")");
            } catch (IndexOutOfBoundsException e) {
                isBracket = true;
            }

            if (isBracket) {
                mValuesList.add(++focusedIndex, null);
                mValuesList.add(focusedIndex + 1, ")");
            }
        }

        return focusedIndex;
    }

    private boolean isStop(int index) {
        try {
            return index < 0 || index >= mValuesList.size() || mValuesList.get(index).equals("(") || mValuesList.get(index).equals(")");
        } catch (NullPointerException e) {
            return true;
        }
    }

    public String fields() {
        StringBuffer result = new StringBuffer();
        for (String character: mValuesList) {
            if (character != null)
                result.append(character);
        }
        return result.toString();
    }

    public void onResume(ArrayList<String> input) {
        mValuesList = input;
        notifyDataSetChanged();
    }

    public void changePosition(int index) {
        if (index >= 0 && index < mValuesList.size())
            pendingFocusIndex.setValue(index);
    }

    public List<String> getValuesList() {
        return mValuesList;
    }

    class EditorViewHolder extends RecyclerView.ViewHolder {

        final EditText field;
        final CalculatorEditorViewAdapter adapter;

        EditorViewHolder(@NonNull View view, final CalculatorEditorViewAdapter adapter) {
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

    public interface OnFieldChangedCallback {

        void onFieldChange(int primaryCode, int index);

    }

}
