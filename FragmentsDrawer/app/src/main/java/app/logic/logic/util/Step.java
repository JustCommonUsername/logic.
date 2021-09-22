package app.logic.logic.util;

import android.util.Pair;

import androidx.annotation.NonNull;

public class Step {

    public final Pair<String, String> step;
    public final StepInterval manual, changed;

    public Step(@NonNull Pair<String, String> step, StepInterval manual, StepInterval changed) {
        this.step = step;
        this.manual = manual;
        this.changed = changed;
    }

}
