package com.faraz.app.moneytap.data_manager.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.faraz.app.moneytap.data_manager.api.Page;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by root on 2/9/18.
 */

@Dao
public interface PageDao {

    @Query("SELECT * FROM Page Where title LIKE :search")
    Single<List<Page>> getAllPages(String search);

    @Insert
    void insertAll(Page... page);



}
