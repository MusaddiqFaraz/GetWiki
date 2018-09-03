package com.faraz.app.moneytap.data_manager.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.faraz.app.moneytap.MoneyTap;
import com.faraz.app.moneytap.data_manager.api.Page;
import com.faraz.app.moneytap.data_manager.api.SearchQuery;

/**
 * Created by root on 2/9/18.
 */

@Database(entities = {Page.class, SearchQuery.class},version = 1)
@TypeConverters(TermsConverter.class)
public abstract class MoneyTapDB extends RoomDatabase{


    public abstract PageDao getPageDao();

    public abstract SearchDao getSearchDao();
}
