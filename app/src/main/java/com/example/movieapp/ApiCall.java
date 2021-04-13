package com.example.movieapp;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiCall {
    @GET("movie/top_rated")
    Call<AllMovieItems> getMovieList(@QueryMap HashMap<String,Object> params);
}
