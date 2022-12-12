package com.example.moody;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.moody.database.AppDatabase;
import com.example.moody.database.Feeling;
import com.example.moody.database.dao.DailyActivityDao;
import com.example.moody.database.dao.MoodDao;
import com.example.moody.database.entity.DailyActivity;
import com.example.moody.database.entity.Mood;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import kotlin.random.Random;

@RunWith(AndroidJUnit4.class)
public class DailyActivityDaoTest {
    // write tests similar to SimpleMoodDaoTest
    private DailyActivityDao dailyActivityDao;
    private AppDatabase db;

    @Before
    @Test
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        dailyActivityDao = db.dailyActivityDao();
    }

    @After
    @Test
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeDailyActivity() throws Exception {
        DailyActivity dailyActivity = new DailyActivity();
        dailyActivity.id = 5;
        dailyActivity.name = "Running";
        dailyActivityDao.insert(dailyActivity);
        List<DailyActivity> allDailyActivities = dailyActivityDao.getAll();
        assertEquals(allDailyActivities.get(0).id, dailyActivity.id);
    }

    @Test
    public void writeDailyActivityAndReadInList() throws Exception {
        DailyActivity dailyActivity = new DailyActivity();
        dailyActivity.id = 2;
        dailyActivity.name = "Jogging";
        dailyActivityDao.insert(dailyActivity);
        List<DailyActivity> allDailyActivities = dailyActivityDao.getAll();
        assertEquals(allDailyActivities.get(0).name, dailyActivity.name);
    }

    @Test
    public void writeDailyActivityAndReadById() throws Exception {
        DailyActivity dailyActivity = new DailyActivity();
        dailyActivity.id = 2;
        dailyActivity.name = "Jogging";
        dailyActivityDao.insert(dailyActivity);
        DailyActivity activity = dailyActivityDao.getById(dailyActivity.id);
        assertEquals(dailyActivity.id, activity.id);
    }

    @Test
    public void updateDailyActivity() {
        // write a test that updates a DailyActivity
        int id = Random.Default.nextInt();

        DailyActivity dailyActivity = new DailyActivity();
        dailyActivity.id = id;
        dailyActivity.name = "Jogging";
        dailyActivityDao.insert(dailyActivity);
        dailyActivity.name = "Running";
        dailyActivityDao.update(dailyActivity);
        DailyActivity activity = dailyActivityDao.getById(id);
        assertEquals(dailyActivity.name, activity.name);
    }
}
