package com.example.moody.entry;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.moody.MainActivity;
import com.example.moody.R;
import com.example.moody.database.AppDatabase;
import com.example.moody.database.entity.DailyActivity;
import com.example.moody.database.entity.DailyActivityGroup;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

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
        List<String> checkedActivities = getCheckedActivities();

        // get all gratitude entries
        List<String> gratitudeList = new ArrayList<>();
        String firstGratitude = ((EditText) findViewById(R.id.firstGratitudeEditText)).getText().toString();
        String secondGratitude = ((EditText) findViewById(R.id.secondGratitudeEditText)).getText().toString();
        String thirdGratitude = ((EditText) findViewById(R.id.thirdGratitudeEditText)).getText().toString();
        gratitudeList.add(firstGratitude);
        gratitudeList.add(secondGratitude);
        gratitudeList.add(thirdGratitude);

        String event = ((EditText) findViewById(R.id.eventInputEditText)).getText().toString();
        entryViewModel.addMood(feelingPicked, checkedActivities, gratitudeList, event);
    }

    public void goToMain(View view) {
        // go to main activity
        // TODO: TEMP CODE, USE BACKSTACK INSTEAD
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @NonNull
    private List<String> getCheckedActivities() {
        ViewGroup activitiesGroupsContainer = findViewById(R.id.activityGroupsContainer);
        List<String> checkedActivities = new ArrayList<>();
        for (int index = 0; index < activitiesGroupsContainer.getChildCount(); index++) {
            ViewGroup nextChild = (ViewGroup) activitiesGroupsContainer.getChildAt(index);
            // hard-coded values are ok because the Mood card layout will not change
            ChipGroup chips = (ChipGroup) ((ViewGroup) nextChild.getChildAt(0)).getChildAt(1);
            for (int chipIndex = 0; chipIndex < chips.getChildCount(); chipIndex++) {
                Chip nextChip = (Chip) chips.getChildAt(chipIndex);
                if (nextChip.isChecked())
                    checkedActivities.add(nextChip.getText().toString());
            }
        }
        return checkedActivities;
    }
}
