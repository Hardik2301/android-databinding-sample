package com.imac.databinding.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.imac.databinding.data.Task;

import java.util.List;

/**
 * Created by imac on 12/25/17.
 */

@Dao
public interface TaskDao {

    @Query("Select * from Task")
    LiveData<List<Task>> getTasksList();

    @Query("Select * from Task where id = :id")
    Task getTask(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void SaveTask(Task task);
}
