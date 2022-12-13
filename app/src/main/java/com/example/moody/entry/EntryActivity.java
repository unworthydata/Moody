package com.example.moody.entry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.moody.MainActivity;
import com.example.moody.R;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

import xyz.teamgravity.imageradiobutton.GravityImageRadioButton;
import xyz.teamgravity.imageradiobutton.GravityRadioGroup;

public class EntryActivity extends AppCompatActivity {
    private EntryViewModel entryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        entryViewModel = new ViewModelProvider(this).get(EntryViewModel.class);

        Button addEventButton = findViewById(R.id.addEventButton);

        addEventButton.setOnClickListener(v -> {
            // set the container to be invisible as opposed to the button
            // because the margins/padding/etc of the container will mess up the view
            findViewById(R.id.addEventButtonContainer).setVisibility(View.GONE);
            findViewById(R.id.eventEditTextContainer).setVisibility(View.VISIBLE);
        });
    }

    public void addEntry(View view) {
        // get all views in layout_entry
        // pass info to view model

        // get chosen feeling
        GravityRadioGroup feelingGroup = findViewById(R.id.gravityRadioGroup);
        GravityImageRadioButton feelingPicked = findViewById(feelingGroup.checkedRadioButtonId());

        // get chosen activities
        ChipGroup activityChips = findViewById(R.id.activitiesChipGroup);
        List<Integer> checkedActivityChips = activityChips.getCheckedChipIds();

        // get all gratitude entries
        List<String> gratitudeList = new ArrayList<>();
        String firstGratitude = ((EditText) findViewById(R.id.firstGratitudeEditText)).getText().toString();
        String secondGratitude = ((EditText) findViewById(R.id.secondGratitudeEditText)).getText().toString();
        String thirdGratitude = ((EditText) findViewById(R.id.thirdGratitudeEditText)).getText().toString();
        gratitudeList.add(firstGratitude);
        gratitudeList.add(secondGratitude);
        gratitudeList.add(thirdGratitude);

        String event = ((EditText) findViewById(R.id.eventInputEditText)).getText().toString();
        entryViewModel.addMood(feelingPicked, checkedActivityChips, gratitudeList, event);
    }

    public void goToMain(View view) {
        // go to main activity
        // TODO: TEMP CODE, USE BACKSTACK INSTEAD
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}