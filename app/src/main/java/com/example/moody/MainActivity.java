package com.example.moody;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.moody.R;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        NavigationBarView navigationBar = findViewById(R.id.bottom_nav);
//        navigationBar.setOnItemSelectedListener(item -> {
//            switch (item.getItemId()) {
//                case R.id.item_main:
//                    navigationBar.setIcon
////                    getSupportFragmentManager().beginTransaction()
////                            .replace(R.id.fragment_container, new HomeFragment())
////                            .commit();
//                    return true;
//                case R.id.item_calendar:
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.fragment_container, new SearchFragment())
//                            .commit();
//                    return true;
//                case R.id.item_entry:
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.fragment_container, new AddFragment())
//                            .commit();
//                    return true;
//                case R.id.item_profile:
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.fragment_container, new ProfileFragment())
//                            .commit();
//                    return true;
//                case R.id.item_profile:
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.fragment_container, new SettingsFragment())
//                            .commit();
//                    return true;
//            }
//            return false;
//        });
    }
}