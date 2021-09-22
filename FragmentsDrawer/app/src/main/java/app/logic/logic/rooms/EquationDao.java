package app.logic.logic.rooms;

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

    @Query("SELECT * FROM calculator_history ORDER BY date DESC")
    LiveData<List<Equation>> getAllEquations();

    @Query("SELECT * FROM calculator_history WHERE function = :function")
    LiveData<List<Equation>> getGivenEquation(String function);

    @Query("DELETE FROM calculator_history WHERE date = :date")
    void delete(long date);

}
