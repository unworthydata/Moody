package com.example.moody.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.moody.database.entity.DailyActivity;
import com.example.moody.database.entity.DailyActivityGroup;
import com.example.moody.database.entity.Gratitude;
import com.example.moody.database.entity.Mood;
import com.example.moody.database.entity.MoodDailyActivity;

@Database(entities = {Mood.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract Mood moodDao();
    public abstract DailyActivity dailyActivityDao();
    public abstract Gratitude gratitudeDao();
    public abstract MoodDailyActivity moodDailyActivityDao();
    public abstract DailyActivityGroup dailyActivityGroupDao();
}
