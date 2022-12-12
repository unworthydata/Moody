package com.example.moody.database.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.DeleteTable;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moody.database.entity.Mood;

import java.util.Date;
import java.util.List;

@Dao
public interface MoodDao {
    String tableName = "mood";

    @Insert
    void insert(Mood mood);

    @Query("SELECT * FROM " + tableName)
    List<Mood> getAll();

    @Query("SELECT * FROM " + tableName + " WHERE id = :id")
    Mood getById(int id);

    @Query("SELECT * FROM " + tableName + " WHERE date = :date")
    Mood getByDate(Date date);

    @Update
    void update(Mood mood);

    @Query("DELETE FROM " + tableName + " WHERE id = :id")
    void deleteById(int id);

    @Query("DELETE FROM " + tableName)
    void deleteAll();
}

