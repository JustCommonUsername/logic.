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

    public Equation getSolvedEquation(String equation) {
        return mEquationDao.getSolvedEquation(equation).getValue().get(0);
    }

    public void insert(Equation equation) {
        new InsertEquationTask(mEquationDao).execute(equation);
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

    private class FetchData extends Thread {

        LiveData<List<Equation>> equation;
        EquationDao dao;

        FetchData(MutableLiveData<List<Equation>> equation, EquationDao dao) {
            this.equation = equation;
            this.dao = dao;
        }

        @Override
        public void run() {
            equation = dao.getAllEquations();
        }

    }

}
