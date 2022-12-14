package com.example.moody.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moody.database.entity.DailyActivity;

import java.util.List;

@Dao
public interface DailyActivityDao {
    String tableName = "DailyActivity";

    @Insert
    void insert(DailyActivity dailyActivity);

    @Query("SELECT * FROM " + tableName)
    LiveData<List<DailyActivity>> getAll();

    @Query("SELECT * FROM " + tableName + " WHERE id = :id")
    DailyActivity get(int id);

    @Query("SELECT * FROM " + tableName + " WHERE name = :name")
    DailyActivity get(String name);

    @Update
    void update(DailyActivity dailyActivity);

    @Query("DELETE FROM " + tableName + " WHERE id = :id")
    void deleteById(int id);

    @Delete
    void delete(DailyActivity dailyActivity);

    @Query("DELETE FROM " + tableName)
    void deleteAll();

    @Query("SELECT name FROM " + tableName + " WHERE dailyactivitygroup_id = :dailyactivitygroup_id")
    List<String> getMembers(int dailyactivitygroup_id);
}
