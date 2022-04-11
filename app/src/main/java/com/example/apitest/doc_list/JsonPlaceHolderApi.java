package com.example.apitest.doc_list;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET("Employee")
    Call<List<doc>> getDocs();
}
