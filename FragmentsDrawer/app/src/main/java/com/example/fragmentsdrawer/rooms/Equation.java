package com.example.fragmentsdrawer.rooms;

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
    @NonNull
    private String function;

    @NonNull
    private String reducedFunction;

    @NonNull
    private String type;

    @NonNull
    private String howChanged;

    private ArrayList<String> steps;

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

    public ArrayList<String> getSteps() {
        return steps;
    }

    public void setFunction(@NonNull String function) {
        this.function = function;
    }

    public Equation(@NonNull String function, @NonNull String type, @NonNull String howChanged, ArrayList<String> steps,
                    String CNF, String DNF, @NonNull String reducedFunction) {
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
