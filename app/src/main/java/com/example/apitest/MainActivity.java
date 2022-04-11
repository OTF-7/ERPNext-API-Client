package com.example.apitest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apitest.databinding.ActivityMainBinding;
import com.example.apitest.databinding.ApiConnectedDialogBinding;
import com.example.apitest.doc_list.DocListActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.dialog.MaterialDialogs;
import com.google.gson.Gson;


import java.util.Objects;

import retrofit2.Retrofit;

public class MainActivity extends Activity {
    ActivityMainBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restaurant.partner-cons.com/")
                .addConverterFactory()

        mBinding.hasSslSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    mBinding.hasSslSwitch.setText("https");
                else
                    mBinding.hasSslSwitch.setText("http");
            }
        });
        mBinding.connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate())
                    return;
                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://www.google.com";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                Toast.makeText(MainActivity.this, "Response is: " + response.substring(0,500), Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"That didn't work!", Toast.LENGTH_SHORT).show();
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
                if(Objects.requireNonNull(mBinding.apiKeyField.getEditText()).getText().toString().equals("123") &&
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
        String url = mBinding.urlField.getEditText().getText().toString();
        if(url.startsWith("http"))
            if(url.startsWith("https")) {
                mBinding.hasSslSwitch.setChecked(true);
                url = url.substring(8);
            }
            else {
                mBinding.hasSslSwitch.setChecked(false);
                url = url.substring(7);
            }
        mBinding.urlField.getEditText().setText(url);

        return false;
    }
}