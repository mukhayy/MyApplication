package com.example.user.myapplication.Network;


import com.example.user.myapplication.Models.DataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("data/2.5/group?units=metric")
    Call<DataResponse> getData(@Query("id") String id, @Query("appid") String appid);
//id=1512569,2643743,745042,2964574,2643123,524901,703448,1217662,1850147,6359304,6454307
 //&appid=6f341c784e7340375409af9fc6b7680e
}

