package com.abhishek.teamworktask;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public class Api {
    String BASE_URL = "https://jsonplaceholder.typicode.com/";

    //getting the GET request from the JSON Array
    @GET("photos");
    Call<List<FetchRetrofitData>> listCall;
}
