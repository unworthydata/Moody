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
    public int id;
    // foreign keys
    public Integer mood_id;
    public Integer dailyactivity_id;
}
