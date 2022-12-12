package com.example.moody.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moody.database.entity.Gratitude;
import com.example.moody.database.entity.Mood;

import java.util.List;

@Dao
public interface GratitudeDao {
    String tableName = "gratitude";

    @Insert
    void insert(Gratitude gratitude);

    @Query("SELECT * FROM " + tableName)
    List<Gratitude> getAll();

    @Update
    void update(Gratitude gratitude);

    @Delete
    void delete(Gratitude gratitude);
}

