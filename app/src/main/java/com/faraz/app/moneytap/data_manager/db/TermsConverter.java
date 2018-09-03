package com.faraz.app.moneytap.data_manager.db;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

/**
 * Created by root on 2/9/18.
 */

public class TermsConverter {

    Gson gson =new Gson();

    @TypeConverter
    public List<String> stringToStringList(String data) {
        if(data == null){
            return Collections.emptyList();
        }


        Type type = new TypeToken<List<String>>() {

        }.getType();


        return gson.fromJson(data,type);
    }

    @TypeConverter
    public String listToString(List<String> strings){
        return gson.toJson(strings);
    }
}
