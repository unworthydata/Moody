package com.example.moody.entry;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.moody.R;
import com.example.moody.database.Feeling;
import com.example.moody.database.entity.DailyActivity;
import com.example.moody.database.entity.DailyActivityGroup;
import com.example.moody.database.entity.Gratitude;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyz.teamgravity.imageradiobutton.GravityImageRadioButton;

public class EntryViewModel extends ViewModel {
    private EntryModel entryModel;
    private Context context;

    public EntryViewModel() {
        entryModel = new EntryModel(context);
    }

    public void addMood(GravityImageRadioButton feelingPicked, List<String> checkedDailyActivities,
                        List<String> gratitudeList, String event) {
        // pass info to model class to entry an entry into the DB
        // go to MainActivity

        Feeling feeling = getFeeling(feelingPicked);

        // remove all empty strings from gratitudeList
        gratitudeList.removeIf(String::isEmpty);

        // possible to add an error because the following code returns a boolean
        entryModel.addMood(feeling, checkedDailyActivities, gratitudeList, event);
    }

    // put here instead of EntryActivity because we want to keep the view as clean as possible
    // and because View classes should not know about internal data representation
    // (in this case, Feeling enum)
    @NonNull
    private Feeling getFeeling(GravityImageRadioButton feelingPicked) {
        Feeling feeling = null;

        switch (feelingPicked.getId()) {
            case R.id.fantasticButton:
                feeling = Feeling.FANTASTIC;
                break;
            case R.id.goodButton:
                feeling = Feeling.GOOD;
                break;
            case R.id.okayButton:
                feeling = Feeling.OKAY;
                break;
            case R.id.badButton:
                feeling = Feeling.BAD;
                break;
            case R.id.terribleButton:
                feeling = Feeling.TERRIBLE;
                break;
            default:
                throw new IllegalArgumentException("Invalid feeling passed to " + this.getClass().getName());
        }
        return feeling;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
