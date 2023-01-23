package com.example.successto_dolist.Adapter;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.successto_dolist.Model.ListModel;
import com.example.successto_dolist.Utilties.DateUtilities;
import com.example.successto_dolist.databinding.TaskCompletedAdapterBinding;

import java.util.List;

public class ListCompletedAdapter extends RecyclerView.Adapter<ListCompletedAdapter.ViewHolder> {

    private List<ListModel> modelList;
    private AdapterListener adapterListener;

    public ListCompletedAdapter(List<ListModel> modelList, AdapterListener adapterListener) {
        this.modelList = modelList;
        this.adapterListener = adapterListener;
    }

    @NonNull
    @Override
    public ListCompletedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(TaskCompletedAdapterBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListCompletedAdapter.ViewHolder holder, int position) {
        ListModel listModel = modelList.get(position);
        holder.binding.tvTodo.setText(listModel.getTodo() );
        holder.binding.tvTodo.setPaintFlags(holder.binding.tvTodo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.binding.tvDate.setText(DateUtilities.toString(listModel.getDate()));
        holder.binding.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterListener.onCheck(listModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TaskCompletedAdapterBinding binding;
        public ViewHolder(TaskCompletedAdapterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setData(List<ListModel> data){
        modelList.clear();
        modelList.addAll(data);
        notifyDataSetChanged();
    }

    public interface AdapterListener {
        void onCheck(ListModel listModel);
    }
}
