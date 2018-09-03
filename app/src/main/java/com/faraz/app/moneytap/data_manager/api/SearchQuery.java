package com.faraz.app.moneytap.data_manager.api;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import javax.annotation.Nonnull;

/**
 * Created by root on 2/9/18.
 */

@Entity
public class SearchQuery {

    @PrimaryKey
    @NonNull  private String search;

    public SearchQuery(@NonNull String search) {
        this.search = search;
    }

    @NonNull
    public String getSearch() {
        return search;
    }

    public void setSearch(@NonNull String search) {
        this.search = search;
    }
}
