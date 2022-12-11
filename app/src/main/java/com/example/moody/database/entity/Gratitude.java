package com.example.moody.database.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Mood.class,
        parentColumns = "id",
        childColumns = "mood_id",
        onDelete = ForeignKey.CASCADE)
})
public class Gratitude {
    @PrimaryKey(autoGenerate = true)
    public int id;
    // foreign key
    public Integer mood_id;
    public String gratitude;
}
