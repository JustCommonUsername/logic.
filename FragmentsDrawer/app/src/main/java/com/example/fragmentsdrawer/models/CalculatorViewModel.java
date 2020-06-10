package com.example.fragmentsdrawer.models;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fragmentsdrawer.repositories.EquationRepository;
import com.example.fragmentsdrawer.rooms.Equation;

import java.util.List;

public class CalculatorViewModel extends AndroidViewModel {

    private EquationRepository repository;
    private LiveData<List<Equation>> mAllEquations;
    private MutableLiveData<String> mCurrentEquation = new MutableLiveData<>();
    private MutableLiveData<Boolean> isExceptionOccured = new MutableLiveData<>();

    private Equation solvedEquation;

    public CalculatorViewModel(Application application) {
        super(application);
        repository = EquationRepository.getRepositoryInstance(application);
        mAllEquations = repository.getAllEquations();
    }

    public LiveData<List<Equation>> getAllEquations() {
        return mAllEquations;
    }

    public MutableLiveData<String> getCurrentEquation() {
        return mCurrentEquation;
    }

    public MutableLiveData<Boolean> getIsExceptionOccured() {
        return isExceptionOccured;
    }

    public void setSolvedEquation(Equation equation) {
        this.solvedEquation = equation;
    }

    public Equation getSolvedEquation() {
        return solvedEquation;
    }

    public void insert(Equation equation) {
        repository.insert(equation);
    }

}
