package com.example.kolekcijapogledanihfilmova.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {
    public static Retrofit getRetrofitInstance(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServiceContract.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
    public static EndPointInterface apiInterface(){
        EndPointInterface apiService = getRetrofitInstance().create(EndPointInterface.class);
        return apiService;
    }
}
