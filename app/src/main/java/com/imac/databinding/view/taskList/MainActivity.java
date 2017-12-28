package com.imac.databinding.view.taskList;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.imac.databinding.R;
import com.imac.databinding.data.Task;
import com.imac.databinding.databinding.ActivityMainBinding;
import com.imac.databinding.view.addTask.AddTaskActivity;
import com.imac.databinding.viewModel.TasksListViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.imac.databinding.view.addTask.AddTaskActivity.KEY_TASK_ID;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerview;
    SimpleAdapter mAdapter;

    TasksListViewModel taskViewModel;

    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        taskViewModel = ViewModelProviders.of(this).get(TasksListViewModel.class);

        mRecyclerview = findViewById(R.id.tasklist);
        mAdapter = new SimpleAdapter(new ArrayList<Task>(), taskViewModel);
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        mainBinding.setTasklist(taskViewModel);

        taskViewModel.getmTasksList().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {
                updateRecyclerview(tasks);
            }
        });

        taskViewModel.getNewTaskEvent().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                callAddNewTask();
            }
        });

        taskViewModel.getTaskforDetail().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer taskId) {
                openTaskDetail(taskId);
            }
        });
    }

    private void openTaskDetail(Integer taskId) {
        Intent i = new Intent(this, AddTaskActivity.class);
        i.putExtra(KEY_TASK_ID, taskId);
        startActivityForResult(i, 101);
    }

    private void callAddNewTask() {
        Intent i = new Intent(this, AddTaskActivity.class);
        startActivityForResult(i, 101);
    }

    private void updateRecyclerview(List<Task> tasks) {
        Log.e("updateRecyclerview: ", tasks.size() + "");
        mAdapter.updateItems(tasks);
    }
}
