package com.example.project;


import com.example.project.pojo.DataResponse;
import com.example.project.pojo.ObjectResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET ("entities/getAllIds")
    Call<DataResponse> getAllIds();

    @GET("object/{id}")
    Call<ObjectResponse> getObject(@Path("id") long id);
}
