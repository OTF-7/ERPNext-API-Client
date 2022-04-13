package com.example.apitest.doc_list.interfaces;

import com.example.apitest.doc_list.models.UserData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface JsonUserDataApi {
    @GET("api/method/frappe.auth.get_logged_user")
    Call<UserData> getUserData(@Header("Authorization") String authorizationHeader);
}
