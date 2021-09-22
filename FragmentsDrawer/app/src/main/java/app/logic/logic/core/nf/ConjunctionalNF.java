package app.logic.logic.core.nf;

import androidx.annotation.NonNull;

import java.util.TreeMap;

public class ConjunctionalNF extends NormalForm {

    public ConjunctionalNF(TreeMap<String, Boolean[]> results) {
        super(results);
    }

    @Override
    boolean isRowNeeded(boolean funcResult) {
        return !funcResult;
    }

    @Override
    int countNeededRows(Boolean[] funcResults) {
        int result = 0;

        for (boolean bool: funcResults)
            if (!bool)
                result++;

        return result;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder normalForm = new StringBuilder();
        Boolean[] funcResults = results.get("Function");

        final int neededRows = countNeededRows(funcResults);
        int bracketsCount = 0;

        if (funcResults != null) {
            for (int i = 0; i < funcResults.length; i++) {
                final boolean funcResult = funcResults[i];

                if (!isRowNeeded(funcResult))
                    continue;
                else
                    bracketsCount++;

                if (neededRows > 1)
                    normalForm.append("(");

                for (String var: variables) {
                    normalForm.append(!results.get(var)[i] ? "" : '¬').append(var).append('∨');
                }

                if (neededRows > 1) {
                    normalForm.replace(normalForm.length() - 1, normalForm.length(), ")");
                    normalForm.append(bracketsCount < neededRows ? "∧" : "");
                }
                else if (normalForm.length() > 0)
                    normalForm.deleteCharAt(normalForm.length() - 1);
            }
        }

        return normalForm.toString();
    }

}
