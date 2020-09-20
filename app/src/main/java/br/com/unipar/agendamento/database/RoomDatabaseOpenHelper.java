package br.com.unipar.medicalcare.database;

import android.content.Context;

import androidx.room.Room;

import java.lang.ref.WeakReference;

public class RoomDatabaseOpenHelper {

    private static AppDatabase databaseInstance;
    private static final String DATABASE_NAME = "pacientes";

    public static AppDatabase getDatabase(WeakReference<Context> weakReference) {
        if (databaseInstance == null) {
            databaseInstance = Room.databaseBuilder(weakReference.get(), AppDatabase.class, DATABASE_NAME).build();
        }
        return databaseInstance;
    }
}
