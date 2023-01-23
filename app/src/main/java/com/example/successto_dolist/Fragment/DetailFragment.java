package com.example.successto_dolist.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.successto_dolist.Model.ListModel;
import com.example.successto_dolist.R;
import com.example.successto_dolist.Utilties.DateUtilities;
import com.example.successto_dolist.databinding.FragmentDetailBinding;
import com.example.successto_dolist.databinding.FragmentUpdateBinding;

public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    private ListModel listModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =  FragmentDetailBinding.inflate(inflater, container, false);
        listModel = (ListModel) requireActivity().getIntent().getSerializableExtra("intent_todo");
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupListener();
    }

    @Override
    public void onStart() {
        super.onStart();
        getTodo();
    }

    private void setupListener(){
        binding.ivEditIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(DetailFragment.this).navigate(R.id.action_detailFragment_to_updateFragment);
            }
        });
    }

    private void getTodo(){
        binding.tvFragTodo.setText(listModel.getTodo());
        binding.tvFragDate.setText(DateUtilities.toString(listModel.getDate()));
    }

}