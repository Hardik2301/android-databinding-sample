package com.imac.databinding.view.taskList;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.imac.databinding.R;
import com.imac.databinding.data.Task;
import com.imac.databinding.databinding.ListitemBinding;
import com.imac.databinding.viewModel.TasksListViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imac on 12/25/17.
 */

public class SimpleAdapter extends RecyclerView.Adapter<SampleViewHolder> {

    List<Task> mList;
    private TasksListViewModel taskViewModel;

    public SimpleAdapter(ArrayList<Task> mList, TasksListViewModel taskViewModel) {
        super();
        this.mList = mList;
        this.taskViewModel = taskViewModel;
    }

    @Override
    public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListitemBinding listitemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.listitem, parent, false);
        return new SampleViewHolder(listitemBinding);
    }

    @Override
    public void onBindViewHolder(SampleViewHolder holder, int position) {
        holder.bindconection(mList.get(position));

        TaskItemActionsListener mlistener = new TaskItemActionsListener() {
            @Override
            public void onTaskClicked(Task task) {
                taskViewModel.openTaskDetail(task.getId());
            }
        };

        holder.setListener(mlistener);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void updateItems(List<Task> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }
}
