package com.imac.databinding.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.imac.databinding.data.Task;
import com.imac.databinding.data.local.TaskDatabase;
import com.imac.databinding.utils.SingleLiveEvent;

/**
 * Created by imac on 12/26/17.
 */

public class AddTaskViewModel extends AndroidViewModel {

    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> description = new ObservableField<>();

    public final ObservableBoolean IsNewTask = new ObservableBoolean(true);

    private SingleLiveEvent<Void> mSavetask = new SingleLiveEvent<>();

    private int mTaskid;

    private TaskDatabase mDatabase;

    public AddTaskViewModel(@NonNull Application application) {
        super(application);

        mDatabase = TaskDatabase.getINSTANCE(getApplication());
    }

    public void loadTask(int taskId) {
        if (taskId == 0) {
            IsNewTask.set(true);
        } else {
            IsNewTask.set(false);
            mTaskid = taskId;
            Task task = mDatabase.getTaskDao().getTask(taskId);
            title.set(task.getTitle());
            description.set(task.getDescription());
        }
    }

    public void saveTask() {
        Task task = new Task(title.get(), description.get());
        mDatabase.getTaskDao().SaveTask(task);
        mSavetask.call();
    }

    public void updateTask() {
        Task task = new Task(mTaskid, title.get(), description.get());
        mDatabase.getTaskDao().SaveTask(task);
        mSavetask.call();
    }

    public SingleLiveEvent<Void> onSavetask() {
        return mSavetask;
    }
}
