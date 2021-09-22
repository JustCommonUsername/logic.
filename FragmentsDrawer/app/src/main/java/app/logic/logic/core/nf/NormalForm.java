package app.logic.logic.core.nf;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

abstract class NormalForm {

    protected TreeMap<String, Boolean[]> results;
    protected ArrayList<String> variables;

    NormalForm(TreeMap<String, Boolean[]> results) {
        this.results = results;
        this.variables = new ArrayList<>(results.size() - 1);

        for (Map.Entry<String, Boolean[]> entry: results.entrySet()) {
            if (!entry.getKey().equals("Function"))
                variables.add(entry.getKey());
        }
    }

    abstract boolean isRowNeeded(boolean funcResult);

    abstract int countNeededRows(Boolean[] funcResults);

    @NonNull
    @Override
    public abstract String toString();

}
