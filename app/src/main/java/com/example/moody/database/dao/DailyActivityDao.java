package com.example.moody.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moody.database.entity.DailyActivity;
import com.example.moody.database.entity.Mood;

import java.util.List;

@Dao
public interface DailyActivityDao {
    String tableName = "DailyActivity";

    @Insert
    void insert(DailyActivity dailyActivity);

    @Query("SELECT * FROM " + tableName)
    List<DailyActivity> getAll();

    @Update
    void update(DailyActivity dailyActivity);

    @Delete
    void delete(DailyActivity dailyActivity);

    @Query("SELECT * FROM " + tableName + " WHERE id = :id")
    DailyActivity getById(int id);
}
