package com.example.moody;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.moody.R;
import com.example.moody.calendar.CalendarFragment;
import com.example.moody.entry.EntryFragment;
import com.example.moody.home.HomeFragment;
import com.example.moody.profile.ProfileFragment;
import com.example.moody.stats.StatsFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // default fragment is the home fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();

        NavigationBarView navigationBar = findViewById(R.id.bottom_nav);
        navigationBar.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.item_main:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new HomeFragment())
                            .commit();
                    return true;
                case R.id.item_calendar:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new CalendarFragment())
                            .commit();
                    return true;
                case R.id.item_entry:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new EntryFragment())
                            .commit();
                    return true;
                case R.id.item_stats:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new StatsFragment())
                            .commit();
                    return true;
                case R.id.item_profile:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new ProfileFragment())
                            .commit();
                    return true;
            }
            return false;
        });
    }
}