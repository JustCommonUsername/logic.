package com.example.fragmentsdrawer.models;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.fragmentsdrawer.repositories.EquationRepository;
import com.example.fragmentsdrawer.rooms.Equation;

public class SolutionsViewModel extends AndroidViewModel {

    private EquationRepository repository;

    public SolutionsViewModel(Application application) {
        super(application);
        repository = EquationRepository.getRepositoryInstance(application);
    }

    public Equation getCurrentSolvedEquation() {
        return repository.getCurrentSolvedEquation();
    }

}
