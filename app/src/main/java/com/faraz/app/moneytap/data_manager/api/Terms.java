package com.faraz.app.moneytap.data_manager.api;

import android.arch.persistence.room.TypeConverters;

import com.faraz.app.moneytap.data_manager.db.TermsConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by root on 2/9/18.
 */


public class Terms implements Serializable
{

    @SerializedName("description")
    @Expose
    @TypeConverters(TermsConverter.class)
    private List<String> description = null;
    private final static long serialVersionUID = -1979802041126848282L;

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Terms{" +
                "description=" + description +
                '}';
    }
}


