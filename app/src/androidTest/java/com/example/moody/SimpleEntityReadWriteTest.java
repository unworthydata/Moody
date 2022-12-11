package com.example.moody;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;;

import com.example.moody.database.AppDatabase;
import com.example.moody.database.Feeling;
import com.example.moody.database.dao.MoodDao;
import com.example.moody.database.entity.Mood;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class SimpleEntityReadWriteTest {
    private MoodDao moodDao;
    private AppDatabase db;

    @Before
    @Test
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        moodDao = db.moodDao();
    }

    @After
    @Test
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        Mood mood = new Mood();
        mood.feeling = Feeling.FANTASTIC;
        moodDao.insert(mood);
        List<Mood> allMoods = moodDao.getAll();
        assertEquals(allMoods.get(0).feeling, Feeling.FANTASTIC);
    }
}
