package com.example.kolekcijapogledanihfilmova.net;

import com.example.kolekcijapogledanihfilmova.net.models.DetailSearch;
import com.example.kolekcijapogledanihfilmova.net.models.MasterSearch;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface EndPointInterface {
    @GET("/")
    Call<MasterSearch> getMoviesByName(@QueryMap Map<String, String> options);
    @GET("/")
    Call<DetailSearch> getMoviesById(@QueryMap Map<String, String> options);
}
