package com.faraz.app.moneytap.data_manager.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.faraz.app.moneytap.data_manager.api.SearchQuery;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by root on 2/9/18.
 */

@Dao
public interface SearchDao {

    @Query("SELECT * FROM SearchQuery")
    Single<List<SearchQuery>> getAllQueries();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract Long insertSearch(SearchQuery searchQuery);

}
