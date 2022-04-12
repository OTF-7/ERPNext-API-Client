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
import com.example.apitest.doc_list.DocListActivity;
import com.example.apitest.doc_list.interfaces.JsonDocTypeListApi;
import com.example.apitest.doc_list.interfaces.JsonUserDataApi;
import com.example.apitest.doc_list.models.UserData;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {
    ActivityMainBinding mBinding;
    JsonDocTypeListApi mJsonDocTypeListApi;
    JsonUserDataApi mJsonUserDataApi;
    String url = "", apiKey = "", secretKey = "";
    boolean hasSsl = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.hasSslSwitch.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mBinding.hasSslSwitch.setText("https");
                    hasSsl = true;
                }

                else {
                    mBinding.hasSslSwitch.setText("http");
                    hasSsl = false;
                }
            }
        });

        mBinding.demoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hasSsl = true;
                mBinding.hasSslSwitch.setChecked(hasSsl);
                url = "restaurant.partner-cons.com/";
                mBinding.urlField.getEditText().setText(url);
                apiKey = "172b12330085465";
                mBinding.apiKeyField.getEditText().setText(apiKey);
                secretKey = "4378c88d03f0514";
                mBinding.secretKeyField.getEditText().setText(secretKey);
            }
        });

        mBinding.connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isConnected())
                    return;
//                getDoc();
                getUserData();
            }
        });


    }

    private boolean isConnected() {
        if (isValidated()) {
            Retrofit retrofit;
            String protocol = "";
            if (mBinding.hasSslSwitch.isChecked())
                protocol = "https://";
            else
                protocol = "http://";

            url = Objects.requireNonNull(mBinding.urlField.getEditText())
                    .getText().toString();
            if (!url.endsWith("/"))
                url += "/";

            apiKey = Objects.requireNonNull(mBinding.apiKeyField.getEditText())
                    .getText().toString();

            secretKey = Objects.requireNonNull(mBinding.secretKeyField.getEditText())
                    .getText().toString();

            retrofit = new Retrofit.Builder()
                    .baseUrl(protocol + url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
//            mJsonDocTypeListApi = retrofit.create(JsonDocTypeListApi.class);
            mJsonUserDataApi = retrofit.create(JsonUserDataApi.class);
            return true;
        } else {
            Toast.makeText(getBaseContext(),
                    "Something went wrong!!\nCheck URL, API key, Secret Key," +
                    " \nand Transfer Protocol (http/https)", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private boolean isValidated() {
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
        return url.length() > 12;
    }

    private void getUserData() {
        Call<UserData> call = mJsonUserDataApi.getUserData();
        call.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(@NonNull Call<UserData> call,
                                   @NonNull Response<UserData> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getBaseContext(), "Code: " +
                            response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                UserData data = response.body();

                ApiConnectedDialogBinding dialogBinding =
                        ApiConnectedDialogBinding.inflate(LayoutInflater.from(MainActivity.this));
                AlertDialog.Builder dialogBuilder =
                        new AlertDialog.Builder(MainActivity.this);
                dialogBuilder.setView(dialogBinding.getRoot());
                AlertDialog dialog = dialogBuilder.create();

                dialogBinding.connectedText.setText(dialogBinding.connectedText.getText()
                        .toString()
                        + " As " + data.getMessage());

                dialog.show();
                dialogBinding.connectedOkButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Intent intent = new Intent(MainActivity.this,
                                DocListActivity.class);
                        intent.putExtra("url",url);
                        intent.putExtra("apiKey",apiKey);
                        intent.putExtra("secretKey",secretKey);
                        intent.putExtra("hasSsl",hasSsl);
                        startActivity(intent);
                    }
                });

                Toast.makeText(getBaseContext(), data.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


}