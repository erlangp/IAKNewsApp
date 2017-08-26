package com.herokuapp.erlangparasu.iaknewsapp.rest;

import com.herokuapp.erlangparasu.iaknewsapp.models.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("articles")
    Call<ApiResponse> getArticles(
            @Query("source") String source,
            @Query("apiKey") String apiKey
    );

}
