package com.example.apitest.doc_list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.apitest.R;
import com.example.apitest.databinding.ActivityDocListBinding;

public class DocListActivity extends AppCompatActivity {
    ActivityDocListBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityDocListBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

    }
}