package com.example.project;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static Retrofit retrofit;
    private static Api api;
    private static final String url = "http://demo3005513.mockable.io/api/v1/";

    public Api() {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build();
    }

    public static Api getInstance(){
        if (api == null){
            api = new Api();
        }
        return api;
    }
    public ApiService getApiService (){

        return retrofit.create(ApiService.class);
    }
}
