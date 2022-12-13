package com.example.moody.entry;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.example.moody.database.AppDatabase;
import com.example.moody.database.Feeling;
import com.example.moody.database.dao.DailyActivityDao;
import com.example.moody.database.dao.GratitudeDao;
import com.example.moody.database.dao.MoodDailyActivityDao;
import com.example.moody.database.dao.MoodDao;
import com.example.moody.database.entity.DailyActivity;
import com.example.moody.database.entity.DailyActivityGroup;
import com.example.moody.database.entity.Gratitude;
import com.example.moody.database.entity.Mood;
import com.example.moody.database.entity.MoodDailyActivity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class EntryModel {
    private AppDatabase db;
    private MoodDao moodDao;
    private DailyActivityDao dailyActivityDao;
    private MoodDailyActivityDao moodDailyActivityDao;
    private GratitudeDao gratitudeDao;

    public EntryModel(Context context) {
        db = Room.databaseBuilder(context, AppDatabase.class, "moody-db").build();
        moodDao = db.moodDao();
        dailyActivityDao = db.dailyActivityDao();
        moodDailyActivityDao = db.moodDailyActivityDao();
        gratitudeDao = db.gratitudeDao();
    }

    public boolean addMood(Feeling feeling, List<String> activities, List<String> gratitudeList, String event) {
        Mood newMood = new Mood();
        // set the straightforward fields done first
        newMood.feeling = feeling;

        event = event.trim();
        if (!event.isEmpty())
            newMood.event = event;

        moodDao.insert(newMood);

        // for every activity, add an entry in the MoodDailyActivity table
        for (String activity : activities) {
            MoodDailyActivity moodDailyActivity = new MoodDailyActivity();
            moodDailyActivity.mood_id = newMood.id;
            moodDailyActivity.dailyactivity_id = dailyActivityDao.get(activity).id;
            moodDailyActivityDao.insert(moodDailyActivity);
        }

        for (String gratitude : gratitudeList) {
            // if gratitude is not empty, add it to the database
            gratitude = gratitude.trim();
            if (!gratitude.isEmpty()) {
                Gratitude newGratitude = new Gratitude();
                newGratitude.mood_id = newMood.id;
                newGratitude.gratitude = gratitude;
                gratitudeDao.insert(newGratitude);
            }
        }

        db.close();
        Log.v("DB Operation", "Mood inserted successfully on " + LocalDateTime.now().toString());
        return true;
    }
}
