package com.example.myretrofit.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myretrofit.R;
import com.example.myretrofit.pojo.Employee;

import java.util.List;

import io.reactivex.annotations.NonNull;

public class AdapterEmployee extends RecyclerView.Adapter<AdapterEmployee.EployeeViewHolder> {

private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EployeeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inform_card,viewGroup,false);

        return new EployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EployeeViewHolder eployeeViewHolder, int i) {
Employee employee= employees.get(i);
eployeeViewHolder.textViewName.setText(employee.getFName());
eployeeViewHolder.textViewLName.setText(employee.getLName());

    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    class EployeeViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewName;
        private TextView textViewLName;
        public EployeeViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewName=itemView.findViewById(R.id.textView_label_name);
        textViewLName=itemView.findViewById(R.id.textView_label_last_name);

    }
}
}
