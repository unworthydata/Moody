package com.example.moody.database;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.moody.SingletonAppContext;
import com.example.moody.database.dao.DailyActivityDao;
import com.example.moody.database.dao.DailyActivityGroupDao;
import com.example.moody.database.dao.GratitudeDao;
import com.example.moody.database.dao.MoodDailyActivityDao;
import com.example.moody.database.dao.MoodDao;
import com.example.moody.database.entity.DailyActivity;
import com.example.moody.database.entity.DailyActivityGroup;
import com.example.moody.database.entity.Gratitude;
import com.example.moody.database.entity.Mood;
import com.example.moody.database.entity.MoodDailyActivity;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {
        DailyActivityGroup.class,
        DailyActivity.class,
        MoodDailyActivity.class,
        Gratitude.class,
        Mood.class
}, version = 1)
@TypeConverters({Converters.class})
// singleton pattern
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "moody-db.db";
    private static volatile AppDatabase instance;
    private static volatile boolean databaseSet = false;

    public abstract DailyActivityGroupDao dailyActivityGroupDao();

    public abstract DailyActivityDao dailyActivityDao();

    public abstract MoodDailyActivityDao moodDailyActivityDao();

    public abstract GratitudeDao gratitudeDao();

    public abstract MoodDao moodDao();

    // synchronized because it would be possible to create two instances in multithreading scenarios
    public static synchronized AppDatabase getInstance() {
        // check if another database exists
        if (databaseSet) {
            Log.e("AppDatabase", "Database already set");
            return null;
        }

        if (instance == null)
            instance = Room.databaseBuilder(
                    SingletonAppContext.getContext(), AppDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();

        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private DailyActivityDao dailyActivityDao;
        private DailyActivityGroupDao dailyActivityGroupDao;

        private PopulateDbAsyncTask(AppDatabase db) {
            dailyActivityDao = db.dailyActivityDao();
            dailyActivityGroupDao = db.dailyActivityGroupDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dailyActivityGroupDao.deleteAll();
                // array of dailyactivitygroup names
                String[] dailyActivityGroupNames = {"Reading", "Sleep", "Food", "Health", "Productivity", "Other"};
                for (String name : dailyActivityGroupNames)
                    dailyActivityGroupDao.insert(new DailyActivityGroup(name));

                dailyActivityDao.deleteAll();
                dailyActivityDao.insert(new DailyActivity("Fiction", dailyActivityGroupDao.get("Reading").id));
                dailyActivityDao.insert(new DailyActivity("Non-Fiction", dailyActivityGroupDao.get("Reading").id));
                dailyActivityDao.insert(new DailyActivity("Quran", dailyActivityGroupDao.get("Reading").id));
                dailyActivityDao.insert(new DailyActivity("Good Sleep", dailyActivityGroupDao.get("Sleep").id));
                dailyActivityDao.insert(new DailyActivity("Medium Sleep", dailyActivityGroupDao.get("Sleep").id));
                dailyActivityDao.insert(new DailyActivity("Bad Sleep", dailyActivityGroupDao.get("Sleep").id));
                dailyActivityDao.insert(new DailyActivity("Gym", dailyActivityGroupDao.get("Health").id));
                dailyActivityDao.insert(new DailyActivity("Walk", dailyActivityGroupDao.get("Health").id));
                dailyActivityDao.insert(new DailyActivity("Stretch", dailyActivityGroupDao.get("Health").id));
                dailyActivityDao.insert(new DailyActivity("Meditate", dailyActivityGroupDao.get("Health").id));
            return null;
        }
    }
}


