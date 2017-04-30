package com.development.mobile.andromeda.shimmer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GamesInterface {

    @GET("/anton/core/api.php")
    Call<PostModel> getProjects(@Query("action") String action, @Query("from") String from, @Query("to") String to);
}