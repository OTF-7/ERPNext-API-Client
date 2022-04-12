package com.example.apitest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.apitest.databinding.ActivityMainBinding;
import com.example.apitest.databinding.ApiConnectedDialogBinding;
import com.example.apitest.doc_list.Doc;
import com.example.apitest.doc_list.DocListActivity;
import com.example.apitest.doc_list.JsonPlaceHolderApi;
import com.example.apitest.doc_list.JsonResponse;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {
    ActivityMainBinding mBinding;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    List<Doc> docs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.hasSslSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    mBinding.hasSslSwitch.setText("https");
                else
                    mBinding.hasSslSwitch.setText("http");
            }
        });
        mBinding.connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate())
                    return;
                connect();
                getDocs();

                if (Objects.requireNonNull(mBinding.apiKeyField.getEditText()).getText().toString().equals("123") &&
                        Objects.requireNonNull(mBinding.secretKeyField.getEditText()).getText().toString().equals("123")) {
                    ApiConnectedDialogBinding dialogBinding = ApiConnectedDialogBinding.inflate(LayoutInflater.from(MainActivity.this));
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    dialogBuilder.setView(dialogBinding.getRoot());
                    AlertDialog dialog = dialogBuilder.create();
                    dialog.show();
                    dialogBinding.connectedOkButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            startActivity(new Intent(MainActivity.this, DocListActivity.class));
                        }
                    });
                }
            }
        });


    }

    private boolean validate() {
        String url = Objects.requireNonNull(mBinding.urlField.getEditText()).getText().toString();
        if (url.startsWith("http"))
            if (url.startsWith("https")) {
                mBinding.hasSslSwitch.setChecked(true);
                url = url.substring(8);
            } else {
                mBinding.hasSslSwitch.setChecked(false);
                url = url.substring(7);
            }
        mBinding.urlField.getEditText().setText(url);

        return false;
    }

    private void getDocs() {
        Call<JsonResponse> call = jsonPlaceHolderApi.getDoc();
        call.enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(@NonNull Call<JsonResponse> call, @NonNull retrofit2.Response<JsonResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getBaseContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                JsonResponse jsonResponse = response.body();
                assert jsonResponse != null;
                docs = new ArrayList<>(Arrays.asList(jsonResponse.getData()));
                StringBuilder text = new StringBuilder();
                for (Doc doc : docs){
                    text.append(doc.getName());
                }
                Toast.makeText(getBaseContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<JsonResponse> call, @NonNull Throwable t) {
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void connect(){
        String protocol = "";
        if (mBinding.hasSslSwitch.isChecked())
            protocol = "https://";
        else
            protocol = "http://";
        String url = mBinding.urlField.getEditText().getText().toString();
        if (!url.endsWith("/"))
            url += "/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(protocol + url) // "restaurant.partner-cons.com/"
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
    }
}