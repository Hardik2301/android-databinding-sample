package com.imac.databinding.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.imac.databinding.data.Task;
import com.imac.databinding.data.local.TaskDatabase;
import com.imac.databinding.utils.SingleLiveEvent;

import java.util.List;

/**
 * Created by imac on 12/25/17.
 */

public class TasksListViewModel extends AndroidViewModel {

    private TaskDatabase mDb;

    private LiveData<List<Task>> mTasksList;

    private SingleLiveEvent<Integer> mOpenTask = new SingleLiveEvent<>();

    private SingleLiveEvent<Void> mCallNewTask = new SingleLiveEvent<>();

    public TasksListViewModel(@NonNull Application application) {
        super(application);

        mDb = TaskDatabase.getINSTANCE(this.getApplication());

        mTasksList = mDb.getTaskDao().getTasksList();
    }

    public LiveData<List<Task>> getmTasksList() {
        return mTasksList;
    }

    public void callNewTask() {
        mCallNewTask.call();
    }

    public SingleLiveEvent<Void> getNewTaskEvent() {
        return mCallNewTask;
    }

    public void openTaskDetail(int task) {
        mOpenTask.setValue(task);
    }

    public SingleLiveEvent<Integer> getTaskforDetail() {
        return mOpenTask;
    }
}
