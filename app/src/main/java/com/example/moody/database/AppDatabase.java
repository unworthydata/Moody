package com.example.moody.database;

import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.moody.SingletonAppContext;
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

@Database(entities = {
        DailyActivityGroup.class,
        DailyActivity.class,
        MoodDailyActivity.class,
        Gratitude.class,
        Mood.class
}, version = 1)
@TypeConverters({Converters.class})
// singleton pattern
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "moody-db.db";
    private static volatile AppDatabase instance;
    private static volatile boolean databaseSet = false;

    public abstract DailyActivityGroupDao dailyActivityGroupDao();

    public abstract DailyActivityDao dailyActivityDao();

    public abstract MoodDailyActivityDao moodDailyActivityDao();

    public abstract GratitudeDao gratitudeDao();

    public abstract MoodDao moodDao();

    // synchronized because it would be possible to create two instances in multithreading scenarios
    public static synchronized AppDatabase getInstance() {
        if (instance == null)
            instance = Room.databaseBuilder(
                    SingletonAppContext.getContext(),
                    AppDatabase.class,
                    DB_NAME).build();

        return instance;
    }
}


