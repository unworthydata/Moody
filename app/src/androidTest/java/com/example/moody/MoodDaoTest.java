package com.example.moody;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import android.content.Context;
import android.util.Log;

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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class MoodDaoTest {
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
    public void writeMoodAndDelete() throws Exception {
        // clear mood table
        moodDao.deleteAll();

        Mood mood = new Mood();
        mood.id = 7;
        mood.feeling = Feeling.FANTASTIC;

        moodDao.insert(mood);
        moodDao.deleteById(mood.id);
        List<Mood> allMoods = moodDao.getAll();
        assertEquals(allMoods.size(), 0);
    }

    @Test
    public void writeMood() throws Exception {
        Mood mood = new Mood();
        mood.id = 1;
        moodDao.insert(mood);
        List<Mood> allMoods = moodDao.getAll();
        assertEquals(allMoods.get(0).id, mood.id);
    }

    @Test
    public void writeMoodAndReadInList() throws Exception {
        Mood mood = new Mood();
        mood.feeling = Feeling.FANTASTIC;
        moodDao.insert(mood);
        List<Mood> allMoods = moodDao.getAll();
        assertEquals(allMoods.get(0).feeling, Feeling.FANTASTIC);
    }

    @Test
    public void writeMoodAndReadById() throws Exception {
        Mood mood = new Mood();
        mood.feeling = Feeling.FANTASTIC;
        moodDao.insert(mood);
        Mood moodById = moodDao.getById(1);
        assertEquals(moodById.feeling, Feeling.FANTASTIC);
    }

    @Test
    public void writeMoodAndReadByDate() throws Exception {
        Date date = new Date();
        Mood mood = new Mood();
        mood.feeling = Feeling.FANTASTIC;
        mood.date = date;
        moodDao.insert(mood);
        Mood moodByDate = moodDao.getByDate(date);
        assertEquals(moodByDate.feeling, Feeling.FANTASTIC);
    }

    @Test
    public void updateMood() {
        // write a test that updates a mood
        Mood mood = new Mood();
        mood.id = 5;
        mood.feeling = Feeling.FANTASTIC;
        moodDao.insert(mood);
        mood.feeling = Feeling.GOOD;
        moodDao.update(mood);
        Mood moodById = moodDao.getById(mood.id);
        assertEquals(moodById.feeling, Feeling.GOOD);
    }
}
