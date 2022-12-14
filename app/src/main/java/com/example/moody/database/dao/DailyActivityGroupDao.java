package com.example.moody.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moody.database.entity.DailyActivityGroup;

import java.util.List;

@Dao
public interface DailyActivityGroupDao {
    String tableName = "DailyActivityGroup";

    @Insert
    void insert(DailyActivityGroup dailyActivityGroup);

    @Query("SELECT * FROM " + tableName)
    List<DailyActivityGroup> getAll();

    @Update
    void update(DailyActivityGroup dailyActivityGroup);

    @Query("DELETE FROM " + tableName + " WHERE id = :id")
    void deleteById(int id);

    //get by name method
    @Query("SELECT * FROM " + tableName + " WHERE name = :name")
    DailyActivityGroup get(String name);

    @Query("DELETE FROM " + tableName)
    void deleteAll();
}
