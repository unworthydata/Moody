package com.example.moody.entry;

import android.view.View;

import androidx.lifecycle.ViewModel;

public class EntryViewModel extends ViewModel {
    private EntryModel entryModel;

    public EntryViewModel() {
        entryModel = new EntryModel();
    }

    public boolean addEntry() {
        // pass info to model class to entry an entry into the DB
        // if all ok, go to HomeFragment
        // otherwise show error on the correct parts of View
        return false;
    }
}
