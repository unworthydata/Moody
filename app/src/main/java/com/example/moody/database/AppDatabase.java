package com.example.moody.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

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
public abstract class AppDatabase extends RoomDatabase {
    public abstract DailyActivityGroupDao dailyActivityGroupDao();

    public abstract DailyActivityDao dailyActivityDao();

    public abstract MoodDailyActivityDao moodDailyActivityDao();

    public abstract GratitudeDao gratitudeDao();

    public abstract MoodDao moodDao();
}


