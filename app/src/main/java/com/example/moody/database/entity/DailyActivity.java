package com.example.moody.database.entity;

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
    public int id;
    public String name;
    //foreign key
    public Integer dailyactivitygroup_id;

    public DailyActivity(String name, Integer dailyactivitygroup_id) {
        this.name = name;
        this.dailyactivitygroup_id = dailyactivitygroup_id;
    }
}
