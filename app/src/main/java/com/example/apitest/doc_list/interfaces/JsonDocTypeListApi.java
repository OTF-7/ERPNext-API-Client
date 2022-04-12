package com.example.apitest.doc_list.interfaces;

import com.example.apitest.doc_list.responses.JsonDocTypeListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface JsonDocTypeListApi {
    @Headers("Authorization: token 172b12330085465:4378c88d03f0514")
    @GET("api/resource/{doctype}")
    Call<JsonDocTypeListResponse> getDoc(@Path("doctype") String doctype);
}
