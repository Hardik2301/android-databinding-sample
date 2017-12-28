package com.imac.databinding.view.addTask;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.imac.databinding.R;
import com.imac.databinding.databinding.AddTaskBinding;
import com.imac.databinding.viewModel.AddTaskViewModel;

/**
 * Created by imac on 12/26/17.
 */

public class AddTaskActivity extends AppCompatActivity {

    public static final String KEY_TASK_ID = "task_id";
    AddTaskBinding addTaskBinding;

    AddTaskViewModel mModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.add_task);

        addTaskBinding = DataBindingUtil.setContentView(this, R.layout.add_task);

        mModel = ViewModelProviders.of(this).get(AddTaskViewModel.class);

        if (getIntent().getExtras() != null) {
            int tadkId = getIntent().getIntExtra(KEY_TASK_ID, 0); // todo
            mModel.loadTask(tadkId);
        }

        addTaskBinding.setAddtask(mModel);

        mModel.onSavetask().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                onsavedtask();
            }
        });
    }

    private void onsavedtask() {
        setResult(RESULT_OK);
        finish();
    }
}
