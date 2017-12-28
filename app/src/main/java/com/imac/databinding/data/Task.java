package com.imac.databinding.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by imac on 12/25/17.
 */

@Entity
public class Task {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "title")
    private String mTitle;

    @ColumnInfo(name = "desc")
    private String mDescription;

    public Task(String title, String description) {
        mTitle = title;
        mDescription = description;
    }

    public Task(int taskId, String title, String description) {
        mId = taskId;
        mTitle = title;
        mDescription = description;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
