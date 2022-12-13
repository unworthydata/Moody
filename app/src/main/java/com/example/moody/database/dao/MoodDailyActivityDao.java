package com.example.moody.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moody.database.entity.MoodDailyActivity;

import java.util.List;

@Dao
public interface MoodDailyActivityDao {
    String tableName = "MoodDailyActivity";

    @Insert
    void insert(MoodDailyActivity moodDailyActivity);

    @Query("SELECT * FROM " + tableName)
    List<MoodDailyActivity> getAll();

    @Update
    void update(MoodDailyActivity moodDailyActivity);
}

