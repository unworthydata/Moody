package com.example.moody.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.example.moody.database.entity.MoodDailyActivity;

import java.util.List;

@Dao
public interface MoodDailyActivityDao {
    String tableName = "MoodDailyActivity";

    @Query("SELECT * FROM " + tableName)
    List<MoodDailyActivity> getAll();

    @Query("SELECT * FROM " + tableName + " WHERE mood_id = :moodId")
    List<MoodDailyActivity> getAllByMoodId(int moodId);

    @Delete
    void delete(MoodDailyActivity moodDailyActivity);
}

