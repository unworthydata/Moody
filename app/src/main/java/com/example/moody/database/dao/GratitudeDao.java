package com.example.moody.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.example.moody.database.entity.Gratitude;
import com.example.moody.database.entity.Mood;

import java.util.List;

@Dao
public interface GratitudeDao {
    String tableName = "gratitude";

    @Query("SELECT * FROM " + tableName)
    List<Gratitude> getAll();

    @Delete
    void delete(Gratitude gratitude);
}

