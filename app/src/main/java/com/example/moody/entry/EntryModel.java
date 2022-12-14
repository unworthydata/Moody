package com.example.moody.entry;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.moody.database.AppDatabase;
import com.example.moody.database.Feeling;
import com.example.moody.database.dao.DailyActivityDao;
import com.example.moody.database.dao.DailyActivityGroupDao;
import com.example.moody.database.dao.GratitudeDao;
import com.example.moody.database.dao.MoodDailyActivityDao;
import com.example.moody.database.dao.MoodDao;
import com.example.moody.database.entity.DailyActivity;
import com.example.moody.database.entity.DailyActivityGroup;
import com.example.moody.database.entity.Gratitude;
import com.example.moody.database.entity.Mood;
import com.example.moody.database.entity.MoodDailyActivity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class EntryModel {
    private AppDatabase db = AppDatabase.getInstance();
    private MoodDao moodDao = db.moodDao();
    private DailyActivityDao dailyActivityDao = db.dailyActivityDao();
    private MoodDailyActivityDao moodDailyActivityDao = db.moodDailyActivityDao();
    private GratitudeDao gratitudeDao = db.gratitudeDao();
    private DailyActivityGroupDao dailyActivityGroupDao = db.dailyActivityGroupDao();
    private static EntryModel instance;

    public boolean addMood(Feeling feeling, List<String> activities, List<String> gratitudeList, String event) {
        // get room database
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

    public Map<String, List<String>> getDailyActivities() {
        setDatabase();
        Map<String, List<String>> activities = new HashMap<>();
        Executors.newSingleThreadExecutor().execute(() -> {
            // for each dailyActivityGroup, get the list of dailyActivities that belong to it
            List<DailyActivityGroup> dailyActivityGroups = dailyActivityGroupDao.getAll();
            for (DailyActivityGroup dailyActivityGroup : dailyActivityGroups) {
                List<String> memberDailyActivities = dailyActivityDao.getMembers(dailyActivityGroup.id);
                activities.put(dailyActivityGroup.name, memberDailyActivities);
                Log.v("DailyActivity1", String.format("Group: %s, Activities: %s", dailyActivityGroup.name, Arrays.toString(memberDailyActivities.toArray())));
            }
        });
        return activities;
    }

    private void setDatabase() {
        if (getInstance() == null) {
            Log.v("DB", "DB created");
            Executors.newSingleThreadExecutor().execute(() -> {
                db.dailyActivityGroupDao().deleteAll();
                // array of dailyactivitygroup names
                String[] dailyActivityGroupNames = {"Reading", "Sleep", "Food", "Health", "Productivity", "Other"};
                for (String name : dailyActivityGroupNames)
                    db.dailyActivityGroupDao().insert(new DailyActivityGroup(name));

                db.dailyActivityDao().deleteAll();
                // add daily activities
                db.dailyActivityDao().insert(new DailyActivity("Fiction", db.dailyActivityGroupDao().get("Reading").id));
                db.dailyActivityDao().insert(new DailyActivity("Non-Fiction", db.dailyActivityGroupDao().get("Reading").id));
                db.dailyActivityDao().insert(new DailyActivity("Quran", db.dailyActivityGroupDao().get("Reading").id));
                db.dailyActivityDao().insert(new DailyActivity("Good Sleep", db.dailyActivityGroupDao().get("Sleep").id));
                db.dailyActivityDao().insert(new DailyActivity("Medium Sleep", db.dailyActivityGroupDao().get("Sleep").id));
                db.dailyActivityDao().insert(new DailyActivity("Bad Sleep", db.dailyActivityGroupDao().get("Sleep").id));
                db.dailyActivityDao().insert(new DailyActivity("Eat Health", db.dailyActivityGroupDao().get("Food").id));
                db.dailyActivityDao().insert(new DailyActivity("Fast Food", db.dailyActivityGroupDao().get("Food").id));
                db.dailyActivityDao().insert(new DailyActivity("Restaurant", db.dailyActivityGroupDao().get("Food").id));
                db.dailyActivityDao().insert(new DailyActivity("Coffee", db.dailyActivityGroupDao().get("Food").id));
                db.dailyActivityDao().insert(new DailyActivity("Gym", db.dailyActivityGroupDao().get("Health").id));
                db.dailyActivityDao().insert(new DailyActivity("Protein Powder", db.dailyActivityGroupDao().get("Health").id));
                db.dailyActivityDao().insert(new DailyActivity("Chamomile Tea", db.dailyActivityGroupDao().get("Health").id));
                db.dailyActivityDao().insert(new DailyActivity("Meditate", db.dailyActivityGroupDao().get("Health").id));
                db.dailyActivityDao().insert(new DailyActivity("Turmeric Supplement", db.dailyActivityGroupDao().get("Health").id));
                db.dailyActivityDao().insert(new DailyActivity("Start Early", db.dailyActivityGroupDao().get("Health").id));
                db.dailyActivityDao().insert(new DailyActivity("Focus", db.dailyActivityGroupDao().get("Productivity").id));
                db.dailyActivityDao().insert(new DailyActivity("Take a break", db.dailyActivityGroupDao().get("Productivity").id));
                db.dailyActivityDao().insert(new DailyActivity("Work", db.dailyActivityGroupDao().get("Productivity").id));
                db.dailyActivityDao().insert(new DailyActivity("Study", db.dailyActivityGroupDao().get("Productivity").id));
                db.dailyActivityDao().insert(new DailyActivity("Attend Lectures", db.dailyActivityGroupDao().get("Productivity").id));
                db.dailyActivityDao().insert(new DailyActivity("Watch TV", db.dailyActivityGroupDao().get("Other").id));
                db.dailyActivityDao().insert(new DailyActivity("Socialize", db.dailyActivityGroupDao().get("Other").id));
                db.dailyActivityDao().insert(new DailyActivity("Documentary", db.dailyActivityGroupDao().get("Other").id));
            });
            Log.v("DB", "DB set already!");
        }
    }

    public static EntryModel getInstance() {
        if (instance == null)
            instance = new EntryModel();

        return instance;
    }
}
