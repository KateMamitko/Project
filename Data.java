package com.example.project.pojo;

import androidx.room.Entity;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Data {
    @SerializedName("id")
    @Expose private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}