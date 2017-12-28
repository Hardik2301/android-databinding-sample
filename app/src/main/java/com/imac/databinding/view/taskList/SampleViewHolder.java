package com.imac.databinding.view.taskList;

import android.support.v7.widget.RecyclerView;

import com.imac.databinding.data.Task;
import com.imac.databinding.databinding.ListitemBinding;

/**
 * Created by imac on 12/25/17.
 */

public class SampleViewHolder extends RecyclerView.ViewHolder {

    private ListitemBinding listitemBinding;

    public SampleViewHolder(ListitemBinding itemView) {
        super(itemView.getRoot());
        listitemBinding = itemView;
    }

    public void bindconection(Task task) {
        listitemBinding.setSampleTask(task);
    }

    public void setListener(TaskItemActionsListener listener) {
        listitemBinding.setListener(listener);
    }
}
