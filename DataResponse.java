package com.example.project.pojo;

import com.example.project.pojo.Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataResponse {
    @SerializedName("data")
    @Expose
    private List<Data> data = null;

    public DataResponse(List<Data> data) {
        this.data = data;
    }

    public DataResponse() {
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
