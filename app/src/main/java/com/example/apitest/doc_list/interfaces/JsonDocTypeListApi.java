package com.example.apitest.doc_list.interfaces;

import com.example.apitest.doc_list.responses.JsonDocTypeListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface JsonDocTypeListApi {
    @GET("api/resource/{doctype}")
    Call<JsonDocTypeListResponse> getDoc(@Path("doctype") String doctype, @Header("Authorization") String authorizationHeader);
}
