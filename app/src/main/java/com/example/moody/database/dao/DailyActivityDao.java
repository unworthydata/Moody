package com.example.moody.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.example.moody.database.entity.DailyActivity;
import com.example.moody.database.entity.Mood;

import java.util.List;

@Dao
public interface DailyActivityDao {
    String tableName = "DailyActivity";

    @Query("SELECT * FROM " + tableName)
    List<DailyActivity> getAll();

    @Delete
    void delete(DailyActivity dailyActivity);
}
