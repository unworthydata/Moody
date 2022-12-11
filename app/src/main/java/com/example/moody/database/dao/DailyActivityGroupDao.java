package com.example.moody.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.example.moody.database.entity.DailyActivityGroup;

import java.util.List;

@Dao
public interface DailyActivityGroupDao {
    String tableName = "DailyActivityGroup";

    @Query("SELECT * FROM " + tableName)
    List<DailyActivityGroup> getAll();

    @Delete
    void delete(DailyActivityGroup dailyActivityGroup);
}
