package com.example.apitest.doc_list;//package com.example.apitest.employees;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apitest.databinding.ActivityDocListBinding;
import com.example.apitest.databinding.RecyclerviewDocItemBinding;
import com.example.apitest.doc_list.models.Doc;

import java.util.ArrayList;
import java.util.List;

public class DocsAdapter extends RecyclerView.Adapter<DocsAdapter.DocsViewHolder> {
    Context mContext;
    List<Doc> mDocsList;
    private static final String TAG = DocsAdapter.class.getName();
    public DocsAdapter(Context context, List<Doc> docsList){
        mContext = context;
        mDocsList = docsList;
        Log.d(TAG, "DocsAdapter: " + docsList.size());
    }

    @NonNull
    @Override
    public DocsAdapter.DocsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new DocsViewHolder(RecyclerviewDocItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DocsAdapter.DocsViewHolder holder, int position) {
        holder.mBinding.docNameTextview.setText(mDocsList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mDocsList.size();
    }

    public class DocsViewHolder extends RecyclerView.ViewHolder {
        RecyclerviewDocItemBinding mBinding;
        public DocsViewHolder(@NonNull RecyclerviewDocItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}