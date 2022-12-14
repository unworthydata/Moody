package com.example.moody.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.moody.database.dao.DailyActivityDao;
import com.example.moody.database.dao.MoodDao;
import com.example.moody.database.entity.DailyActivity;
import com.example.moody.database.entity.Mood;

import java.util.List;

public class Repository {
    private DailyActivityDao activityDao;
    private LiveData<List<DailyActivity>> allActivities;

    public Repository() {
        AppDatabase database = AppDatabase.getInstance();
        activityDao = database.dailyActivityDao();
        allActivities = activityDao.getAll();
    }

    public void insert(DailyActivity activity) {
        new InsertActivityAsyncTask(activityDao).execute(activity);
    }

    public void update(DailyActivity activity) {
        new UpdateActivityAsyncTask(activityDao).execute(activity);
    }

    public void delete(DailyActivity activity) {
        new DeleteActivityAsyncTask(activityDao).execute(activity);
    }

    public void deleteAllMoods() {
        new DeleteActivityAsyncTask(activityDao).execute();
    }

    // return all notes
    public LiveData<List<DailyActivity>> getAllActivities() {
        return allActivities;
    }

    private static class InsertActivityAsyncTask extends AsyncTask<DailyActivity, Void, Void> {
        private DailyActivityDao activityDao;

        private InsertActivityAsyncTask(DailyActivityDao activityDao) {
            this.activityDao = activityDao;
        }

        @Override
        protected Void doInBackground(DailyActivity... dailyActivities) {
            activityDao.insert(dailyActivities[0]);
            return null;
        }
    }

    private static class UpdateActivityAsyncTask extends AsyncTask<DailyActivity, Void, Void> {
        private DailyActivityDao activityDao;

        private UpdateActivityAsyncTask(DailyActivityDao activityDao) {
            this.activityDao = activityDao;
        }

        @Override
        protected Void doInBackground(DailyActivity... dailyActivities) {
            activityDao.update(dailyActivities[0]);
            return null;
        }
    }

    private static class DeleteActivityAsyncTask extends AsyncTask<DailyActivity, Void, Void> {
        private DailyActivityDao activityDao;

        private DeleteActivityAsyncTask(DailyActivityDao activityDao) {
            this.activityDao = activityDao;
        }

        @Override
        protected Void doInBackground(DailyActivity... dailyActivities) {
            activityDao.delete(dailyActivities[0]);
            return null;
        }
    }
}
