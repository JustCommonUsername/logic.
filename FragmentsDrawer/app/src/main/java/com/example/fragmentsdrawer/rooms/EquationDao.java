package com.example.fragmentsdrawer.rooms;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EquationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Equation equation);

    @Query("DELETE FROM calculator_history")
    void deleteAll();

    @Query("SELECT * FROM calculator_history")
    LiveData<List<Equation>> getAllEquations();

    @Query("SELECT * FROM calculator_history WHERE function = :function")
    LiveData<List<Equation>> getSolvedEquation(String function);

}
