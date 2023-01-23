package com.example.successto_dolist.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.successto_dolist.Activity.MainActivity;
import com.example.successto_dolist.Database.DbClient;
import com.example.successto_dolist.Database.TodoDao;
import com.example.successto_dolist.Model.ListModel;
import com.example.successto_dolist.R;
import com.example.successto_dolist.Utilties.DateUtilities;
import com.example.successto_dolist.databinding.FragmentDetailBinding;
import com.example.successto_dolist.databinding.FragmentUpdateBinding;

public class UpdateFragment extends Fragment {

    private FragmentUpdateBinding binding;
    private ListModel detailTask;
    private ListModel updateTask = new ListModel();
    private TodoDao Data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =  FragmentUpdateBinding.inflate(inflater, container, false);
        detailTask = (ListModel) requireActivity().getIntent().getSerializableExtra("intent_todo");
        Data = DbClient.getInstance(requireActivity()).getDbTodoDao().todoDao();
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

    private void  setupListener(){

        binding.tvFragUpdtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        String datePicked = DateUtilities.toString(year,month,dayOfMonth);
                        updateTask.setDate(DateUtilities.toLong(datePicked));
                        binding.tvFragLblDate.setText(datePicked);
                    }
                };

                DateUtilities.showDialog(requireActivity(),dateSetListener).show();
            }
        });

        binding.btnFragSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateTask.setTodo(binding.etFragTodo.getText().toString());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Data.update(updateTask);
                        requireActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(requireActivity(), "Task Updated", Toast.LENGTH_SHORT).show();
                                requireActivity().finish();
                            }
                        });
                    }
                }).start();
            }
        });

        binding.btnDeleteDlg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Data.delete(updateTask);
                        requireActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(requireActivity(), "Task Deleted", Toast.LENGTH_SHORT).show();
                                requireActivity().finish();
                            }
                        });
                    }
                }).start();
            }
        });

    }

    private void getTodo(){
        updateTask = detailTask;
        binding.etFragTodo.setText(updateTask.getTodo());
        binding.tvFragLblDate.setText(DateUtilities.toString(updateTask.getDate()));

    }
}