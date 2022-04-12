package com.example.apitest.doc_list.interfaces;

import com.example.apitest.doc_list.models.UserData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface JsonUserDataApi {
    @Headers("Authorization: token 172b12330085465:4378c88d03f0514")
    @GET("api/method/frappe.auth.get_logged_user")
    Call<UserData> getUserData();
}
