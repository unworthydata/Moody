package com.example.moody.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(foreignKeys = {@ForeignKey(entity = Gratitude.class,
        parentColumns = "id",
        childColumns = "gratitude_id",
        onDelete = ForeignKey.CASCADE)
})
public class Mood {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private Date date;
    private Feeling feeling;
    // foreign key
    private int gratitude_id;
    private String event;
}
