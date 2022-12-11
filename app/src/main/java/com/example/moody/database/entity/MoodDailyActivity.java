package com.example.moody.database.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Mood.class,
                parentColumns = "id",
                childColumns = "mood_id",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = DailyActivity.class,
                parentColumns = "id",
                childColumns = "dailyactivity_id",
                onDelete = ForeignKey.CASCADE)})
public class MoodDailyActivity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    // foreign keys
    private int mood_id;
    private int dailyactivity_id;
}
