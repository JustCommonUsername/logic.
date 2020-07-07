package com.example.fragmentsdrawer.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fragmentsdrawer.rooms.Equation;
import com.example.fragmentsdrawer.rooms.EquationDao;
import com.example.fragmentsdrawer.rooms.EquationRoomDatabase;

import java.util.List;

public class EquationRepository {

    private EquationDao mEquationDao;
    private LiveData<List<Equation>> mAllEquations;
    private Equation currentSolvedEquation;

    public static EquationRepository INSTANCE;

    public static EquationRepository getRepositoryInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (EquationRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new EquationRepository(application);
                }
            }
        }
        return INSTANCE;
    }

    @WorkerThread
    private EquationRepository(Application application) {
        EquationRoomDatabase db = EquationRoomDatabase.getDatabaseInstance(application);
        mEquationDao = db.equationDao();
        mAllEquations = mEquationDao.getAllEquations();
    }

    public LiveData<List<Equation>> getAllEquations() {
        return mAllEquations;
    }

    public void insert(Equation equation) {
        new InsertEquationTask(mEquationDao).execute(equation);
    }

    public void delete(Equation equation) {
        new DeleteEquationTask(mEquationDao).execute(equation);
    }

    public void setCurrentSolvedEquation(Equation equation) {
        if (equation != null)
            currentSolvedEquation = equation;
    }

    public Equation getCurrentSolvedEquation() {
        return currentSolvedEquation;
    }

    private static class InsertEquationTask extends AsyncTask<Equation, Void, Void> {

        private EquationDao mEquationTaskDao;

        InsertEquationTask(EquationDao dao) {
            mEquationTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Equation... params) {
            mEquationTaskDao.insert(params[0]);
            return null;
        }

    }

    private static class DeleteEquationTask extends AsyncTask<Equation, Void, Void> {

        private EquationDao mEquationDao;

        DeleteEquationTask(EquationDao dao) {
            mEquationDao = dao;
        }

        @Override
        protected Void doInBackground(final Equation... params) {
            mEquationDao.delete(/* Deleting given in parameters equation */params[0].getDate());
            return null;
        }

    }

}
