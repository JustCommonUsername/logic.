package app.logic.logic.rooms;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Equation.class}, version = 12, exportSchema = false)
public abstract class EquationRoomDatabase extends RoomDatabase {

    public abstract EquationDao equationDao();
    public static EquationRoomDatabase INSTANCE;

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@Nullable SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    // Implementing Singleton pattern
    public static EquationRoomDatabase getDatabaseInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (EquationRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), EquationRoomDatabase.class, "app_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
