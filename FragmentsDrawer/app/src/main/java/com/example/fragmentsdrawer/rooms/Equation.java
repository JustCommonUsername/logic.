package com.example.fragmentsdrawer.rooms;

import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.fragmentsdrawer.util.Converters;

import java.util.ArrayList;

@Entity(tableName = "calculator_history")
@TypeConverters({Converters.class})
public class Equation {

    @PrimaryKey
    private long date;

    @NonNull
    private String function;

    @NonNull
    private String reducedFunction;

    @NonNull
    private String type;

    @NonNull
    private String howChanged;

    private ArrayList<Pair<String, String>> steps;

    private String CNF;

    private String DNF;

    @NonNull
    public String getFunction() {
        return function;
    }

    @NonNull
    public String getReducedFunction() {
        return reducedFunction;
    }

    @NonNull
    public String getType() {
        return type;
    }

    @NonNull
    public String getHowChanged() {
        return howChanged;
    }

    public String getCNF() {
        return CNF;
    }

    public String getDNF() {
        return DNF;
    }

    public ArrayList<Pair<String, String>> getSteps() {
        return steps;
    }

    public void setFunction(@NonNull String function) {
        this.function = function;
    }

    public long getDate() {
        return date;
    }

    public Equation(long date, @NonNull String function, @NonNull String type, @NonNull String howChanged, ArrayList<Pair<String, String>> steps,
                    String CNF, String DNF, @NonNull String reducedFunction) {
        this.date = date;
        this.function = function;
        this.type = type;
        this.howChanged = howChanged;
        this.steps = steps;
        this.CNF = CNF;
        this.DNF = DNF;
        this.reducedFunction = reducedFunction;
    }

    @NonNull
    @Override
    public String toString() {
        return function + "; " + type + "; " + howChanged;
    }
}
