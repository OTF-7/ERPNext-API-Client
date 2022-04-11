//package com.example.apitest.employees;
//
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//
//import java.util.ArrayList;
//
//public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesAdapter.EmployeesHolder> {
//
//    private ArrayList<String> employeesList;
//
//    public EmployeesAdapter(ArrayList<String> employeesList) {
//        this.employeesList = employeesList;
//    }
//
//    @NonNull
//    @Override
//    public EmployeesAdapter.EmployeesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        ItemOfEmployeeBinding mBinding = ItemOfEmployeeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
//        return new EmployeesHolder(mBinding);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull EmployeesAdapter.EmployeesHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return employeesList.size();
//    }
//
////    public class EmployeesHolder extends RecyclerView.ViewHolder {
////        private ItemOfEmployeeBinding mBinding;
////
////        public EmployeesHolder(ItemOfEmployeeBinding mBinding) {
////            super(mBinding.getRoot());
////            this.mBinding = mBinding;
////        }
////    }
//}
