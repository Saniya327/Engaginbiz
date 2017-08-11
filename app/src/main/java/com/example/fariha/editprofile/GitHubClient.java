package com.example.fariha.editprofile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Fariha on 10-08-2017.
 */

public interface GitHubClient {
    @Headers("Accept:application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    Call<GitAccessToken>getAccessToken(
        @Field("client_id")String clientid,
        @Field("client_secret")String clientSecret,
        @Field("client_code") String code
    );

    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> reposForUser(@Path("user")String user);

}