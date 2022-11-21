package com.example.retrofit.services;

import com.example.retrofit.models.ListUser;
import com.example.retrofit.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {
    // Get all users
    @GET("users")
    Call<ListUser> getList(@Query("page") int currentPage);

    // Get single user
    @GET("users/{id}")
    Call<User> getUser(@Path("id") int userId);

    // Insert user
    @POST("users")
    Call<User> insertUser(@Body User user);

    // Update user
    @FormUrlEncoded
    @PUT("users/{id}")
    Call<User> updateUser(@Path("id") int userId,
                          @Field("first_name") String firstName,
                          @Field("last_name") String lastName,
                          @Field("email") String email);

    // Delete user
    @DELETE("users/{id}")
    Call<User> deleteUser(@Path("id") int userId);
}
