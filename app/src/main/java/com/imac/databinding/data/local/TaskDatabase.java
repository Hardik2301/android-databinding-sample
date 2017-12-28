package com.imac.databinding.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.imac.databinding.data.Task;

/**
 * Created by imac on 12/25/17.
 */

@Database(entities = {Task.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase {

    private static TaskDatabase INSTANCE;

    private static final Object tLock = new Object();

    public static TaskDatabase getINSTANCE(Context context) {
        synchronized (tLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        TaskDatabase.class, "Tasks.db").allowMainThreadQueries().build();
            }
            return INSTANCE;
        }
    }

    public abstract TaskDao getTaskDao();
}
