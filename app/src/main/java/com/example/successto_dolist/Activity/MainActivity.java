package com.example.successto_dolist.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.example.successto_dolist.Adapter.ListAdapter;
import com.example.successto_dolist.Adapter.ListCompletedAdapter;
import com.example.successto_dolist.Database.DbClient;
import com.example.successto_dolist.Database.TodoDao;
import com.example.successto_dolist.Model.ListModel;
import com.example.successto_dolist.Utilties.DateUtilities;
import com.example.successto_dolist.databinding.ActivityMainBinding;
import com.example.successto_dolist.databinding.AddDialogueBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ListAdapter listAdapter;
    private TodoDao Data;
    private List<ListModel> ListTask;
    private ListCompletedAdapter listCompletedAdapter;
    private List<ListModel> ListCompletedTask;
    private  Long selectedDate = 0L;
    private  ListModel selectedTodo = new ListModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Data = DbClient.getInstance(this).getDbTodoDao().todoDao();
        setupListener();
        setuplist();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getTodo();
    }

    private void setupListener(){
        binding.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog addDialog = new AlertDialog.Builder(MainActivity.this).create();
                AddDialogueBinding addDialogueBinding = AddDialogueBinding.inflate(getLayoutInflater());
                addDialog.setView(addDialogueBinding.getRoot());
                addDialog.setCancelable(true);
                addDialog.show();

                addDialogueBinding.tvDateDlg.setText(DateUtilities.today());
                selectedDate = DateUtilities.toLong(DateUtilities.today());

                addDialogueBinding.tvSetDlg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                                String datePicked = DateUtilities.toString(year,month,dayOfMonth);
                                selectedDate = DateUtilities.toLong(datePicked);
                                addDialogueBinding.tvDateDlg.setText(datePicked);
                                Log.e("selectedDate", selectedDate.toString());
                            }
                        };

                        DateUtilities.showDialog(MainActivity.this, dateSetListener).show();

                    }
                });
                addDialogueBinding.btnAddDlg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        addDialog.dismiss();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {

                                ListModel list = new ListModel();
                                list.setTodo(addDialogueBinding.etAddDlg.getText().toString());
                                list.setCompleted(false);
                                list.setDate(selectedDate);
                                Data.insert(list);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        getTodo();
                                    }
                                });
                            }
                        }).start();
                    }
                });
            }
        });
    }

    private void setuplist(){
        listAdapter = new ListAdapter(new ArrayList<>(), new ListAdapter.AdapterListener() {
            @Override
            public void onCheck(ListModel listModel) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        selectedTodo = listModel;
                        selectedTodo.setCompleted(true);
                        Data.update(selectedTodo);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                getTodo();
                            }
                        });
                    }
                }).start();
            }

            @Override
            public void onTodo(ListModel listModel) {
                startActivity(new Intent(MainActivity.this, EditAktivitas.class).putExtra("intent_todo",listModel));
            }
        });

        binding.rvList.setAdapter(listAdapter);

        listCompletedAdapter = new ListCompletedAdapter(new ArrayList<>(), new ListCompletedAdapter.AdapterListener() {
            @Override
            public void onCheck(ListModel listModel) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        selectedTodo = listModel;
                        selectedTodo.setCompleted(false);
                        Data.update(selectedTodo);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                getTodo();
                            }
                        });
                    }
                }).start();
            }
        });
        binding.rvListCompleted.setAdapter(listCompletedAdapter);
    }

    private void getTodo(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                ListTask = Data.allTodo();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("MainActivity", "toString: " + ListTask.toString());
                        listAdapter.setData(ListTask);
                    }
                });
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                ListCompletedTask = Data.allCompletedTodo();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("MainActivity", "toString: " + ListCompletedTask.toString());
                        listCompletedAdapter.setData(ListCompletedTask);
                    }
                });
            }
        }).start();
    }
}