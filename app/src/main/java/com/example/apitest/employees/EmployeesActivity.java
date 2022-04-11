package com.example.apitest.employees;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apitest.R;
import com.example.apitest.databinding.ActivityEmployeesBinding;

public class EmployeesActivity extends AppCompatActivity {
    ActivityEmployeesBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityEmployeesBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        this.getWindow().setStatusBarColor(getResources().getColor(R.color.Light_Coral));
        setTitle("Encryption and Decryption");
        setSupportActionBar(mBinding.employeesToolbar);

        mBinding.employeesToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBinding.employeesToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}