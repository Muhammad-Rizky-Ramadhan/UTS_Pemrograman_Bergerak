package com.example.uts.data.retrofit;

import com.example.uts.data.response.FindUsers;
import com.example.uts.data.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search/users")
    Call<FindUsers> findUsers(@Query("q") String query);

    @GET("users/{username}")
    Call<UserResponse> getUser(@Path("username") String username);
}