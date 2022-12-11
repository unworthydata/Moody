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
    private int id;
    // foreign key
    private int mood_id;
    private String gratitude;
}
