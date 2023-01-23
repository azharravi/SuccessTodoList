package com.example.successto_dolist.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.successto_dolist.Model.ListModel;
import com.example.successto_dolist.Utilties.DateUtilities;
import com.example.successto_dolist.databinding.TaskAdapterBinding;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<ListModel> modelList;
    private AdapterListener adapterListener;

    public ListAdapter(List<ListModel> modelList, AdapterListener adapterListener) {
        this.modelList = modelList;
        this.adapterListener = adapterListener;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(TaskAdapterBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListModel listModel = modelList.get(position);
        holder.binding.tvTodo.setText(listModel.getTodo() );
        holder.binding.tvDate.setText(DateUtilities.toString(listModel.getDate()));
        holder.binding.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterListener.onCheck(listModel);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterListener.onTodo(listModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TaskAdapterBinding binding;
        public ViewHolder(TaskAdapterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setData(List<ListModel> data){
        modelList.clear();
        modelList.addAll(data);
        notifyDataSetChanged();
    }

    public interface AdapterListener{
        void onCheck(ListModel listModel);
        void onTodo(ListModel listModel);
    }

}
