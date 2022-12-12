package com.example.moody.entry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.moody.MainActivity;
import com.example.moody.R;

public class EntryActivity extends AppCompatActivity {
    private EntryViewModel entryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        entryViewModel = new ViewModelProvider(this).get(EntryViewModel.class);
    }

    public void addEntry(View view) {
        // collect input
        // send to viewmodel

    }

    public void addActivity(View view) {

    }

    public void goToMain(View view) {
        // go to main activity
        // TODO: TEMP CODE, USE BACKSTACK INSTEAD
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}