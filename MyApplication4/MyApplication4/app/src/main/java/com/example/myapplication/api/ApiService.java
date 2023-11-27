package com.example.myapplication.api;

import com.example.myapplication.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyy").create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://653fa0179e8bd3be29e0ee43.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("User")
    Call<List<User>> getListUsers(@Query("userId") int userId);

    @POST("User")
    Call<User> createNew(@Body User user);

    @PUT("User/{id}")
    Call<User> updateUser(@Path("id") Object id, @Body User user);

    @DELETE("User/{id}")
    Call<User> deleteUser(@Path("id") Object id);
}
