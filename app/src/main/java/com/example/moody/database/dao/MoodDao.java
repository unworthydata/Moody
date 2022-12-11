package com.example.moody.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.moody.database.entity.Mood;

import java.util.List;

@Dao
public interface MoodDao {
    String tableName = "mood";

    @Query("SELECT * FROM " + tableName)
    List<Mood> getAll();

    @Insert
    void insert(Mood mood);

    @Delete
    void delete(Mood mood);
}

