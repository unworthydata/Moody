package com.example.moody.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = DailyActivityGroup.class,
        parentColumns = "id",
        childColumns = "dailyactivitygroup_id",
        onDelete = ForeignKey.CASCADE)
})
public class DailyActivity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    //foreign key
    private int dailyactivitygroup_id;
}
