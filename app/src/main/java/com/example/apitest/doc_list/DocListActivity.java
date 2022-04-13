package com.example.apitest.doc_list;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apitest.R;
import com.example.apitest.databinding.ActivityDocListBinding;
import com.example.apitest.doc_list.interfaces.JsonDocTypeListApi;
import com.example.apitest.doc_list.models.Doc;
import com.example.apitest.doc_list.responses.JsonDocTypeListResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DocListActivity extends AppCompatActivity {
    private static final String TAG = DocListActivity.class.getName();
    ActivityDocListBinding mBinding;
    Retrofit retrofit;
    JsonDocTypeListApi mJsonDocTypeListApi;
    List<Doc> docData = new ArrayList<>();
    String url = "", apiKey = "", secretKey = "";
    boolean hasSsl = true;
    DocsAdapter docsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityDocListBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        apiKey = intent.getStringExtra("apiKey");
        secretKey = intent.getStringExtra("secretKey");
        hasSsl = intent.getBooleanExtra("hasSsl", true);
//
//        String text = "URl: " + url +
//                "\nAPI Key: " + apiKey +
//                "\nSecret Key: " + secretKey +
//                "\nHas SSL certificate: " + hasSsl;
//        Toast.makeText(getBaseContext(), text, Toast.LENGTH_SHORT).show();
        String protocol = hasSsl ? "https://" : "http://";
        retrofit = new Retrofit.Builder()
                .baseUrl(protocol + url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mJsonDocTypeListApi = retrofit.create(JsonDocTypeListApi.class);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mBinding.docRecyclerview.setLayoutManager(layoutManager);

        fillSpinner();
        docsAdapter = new DocsAdapter(this, docData);
        mBinding.docRecyclerview.setAdapter(docsAdapter);

    }

    private void fillSpinner() {
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Employee");
        spinnerArray.add("Task");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = findViewById(R.id.doc_spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getDoc(spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void getDoc(String documentType) {
        mBinding.progressBarDocList.setVisibility(View.VISIBLE);
        Call<JsonDocTypeListResponse> call = mJsonDocTypeListApi.getDoc(documentType);
        call.enqueue(new Callback<JsonDocTypeListResponse>() {
            @Override
            public void onResponse(@NonNull Call<JsonDocTypeListResponse> call,
                                   @NonNull retrofit2.Response<JsonDocTypeListResponse> response) {
                mBinding.progressBarDocList.setVisibility(View.GONE);
                if (!response.isSuccessful()) {
                    Toast.makeText(getBaseContext(), "Code: " + response.code(),
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                JsonDocTypeListResponse jsonDocTypeListResponse = response.body();
                assert jsonDocTypeListResponse != null;
                docsAdapter.mDocsList = new ArrayList<>(Arrays.asList(jsonDocTypeListResponse.getData()));
                docsAdapter.notifyDataSetChanged();
                StringBuilder text = new StringBuilder();
                for (Doc data : docData) {
                    text.append(data.getName());
                }
                Toast.makeText(getBaseContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<JsonDocTypeListResponse> call,
                                  @NonNull Throwable t) {
                mBinding.progressBarDocList.setVisibility(View.GONE);
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
//                Toast.makeText(getBaseContext(), "Something went wrong!!\nCheck URL, API key, Secret Key," +
//                        " \nand Transfer Protocol (http/https)", Toast.LENGTH_LONG).show();
            }
        });
    }

}