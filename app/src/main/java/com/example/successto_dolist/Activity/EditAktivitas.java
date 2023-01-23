package com.example.successto_dolist.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.successto_dolist.databinding.ActivityEditAktivitasBinding;

public class EditAktivitas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEditAktivitasBinding binding = ActivityEditAktivitasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}