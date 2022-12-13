package com.example.moody.entry;

import android.view.View;

import androidx.lifecycle.ViewModel;

import com.example.moody.R;
import com.example.moody.database.Feeling;
import com.example.moody.database.entity.DailyActivity;
import com.example.moody.database.entity.Gratitude;
import com.google.android.material.chip.Chip;

import java.util.List;

import xyz.teamgravity.imageradiobutton.GravityImageRadioButton;

public class EntryViewModel extends ViewModel {
    private EntryModel entryModel;

    public EntryViewModel() {
        entryModel = new EntryModel();
    }

    public boolean addMood(GravityImageRadioButton feelingPicked, List<Integer> activityChips, List<String> gratitudeList, String event) {
        // pass info to model class to entry an entry into the DB
        // go to MainActivity

        Feeling feeling = null;

//        switch (feelingPicked.getId()) {
//            case R.id.fantasticButton:
//                entryModel.addMood(Feeling.FANTASTIC, null, null, null);
//            case R.id.goodButton:
//                entryModel.addMood(Feeling.GOOD, null, null, null);
//            case R.id.okayButton:
//                entryModel.addMood(Feeling.OKAY, null, null, null);
//            case R.id.badButton:
//                entryModel.addMood(Feeling.BAD, null, null, null);
//            case R.id.terribleButton:
//                entryModel.addMood(Feeling.TERRIBLE, null, null, null);
//        }
//        entryModel.addMood(null, null, null, null);
        return false;
    }
}
