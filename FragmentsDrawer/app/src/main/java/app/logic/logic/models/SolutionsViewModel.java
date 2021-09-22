package app.logic.logic.models;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import app.logic.logic.repositories.EquationRepository;
import app.logic.logic.rooms.Equation;

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
