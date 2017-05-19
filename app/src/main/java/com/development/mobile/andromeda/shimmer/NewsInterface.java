package com.development.mobile.andromeda.shimmer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface NewsInterface {

    @GET("/v1/articles")
    Call<ModelNews> getNews(@Query("source") String source, @Query("apiKey") String apiKey);
}
