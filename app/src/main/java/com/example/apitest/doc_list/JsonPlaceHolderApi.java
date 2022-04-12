package com.example.apitest.doc_list;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface JsonPlaceHolderApi {
    @Headers("Authorization: token 172b12330085465:4378c88d03f0514")
    @GET("api/resource/Employee")
    Call<JsonResponse> getDoc();
}
