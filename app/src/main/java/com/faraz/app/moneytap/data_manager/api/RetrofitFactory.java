package com.faraz.app.moneytap.data_manager.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 2/9/18.
 */

public class RetrofitFactory {

    private static Retrofit retrofit;

    public static final String BASE_URL = "https://en.wikipedia.org/";

    static final String get = "/w/api.php?action=query&format=json&prop=pageimages%7Cpageterms&generator=prefixsearch&redirects=1&formatversion=2&piprop=thumbnail&pithumbsize=150&pilimit=10&wbptterms=description&gpslimit=10&";
    static final String query = "gpssearch";

    public static Retrofit getRetrofitClient(String baseUrl){
        if(retrofit == null) {


            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            OkHttpClient okHttpClient =new  OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .addNetworkInterceptor(new HttpLoggingInterceptor(message -> Log.i("APIINTERFACE", ": "+message)).setLevel(HttpLoggingInterceptor.Level.BASIC))
                    .build();

            retrofit =new  Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();


        }

        return retrofit;
    }
}
