package com.faraz.app.moneytap.data_manager;

import android.util.Log;

import com.faraz.app.moneytap.R;
import com.faraz.app.moneytap.data_manager.api.ApiInterface;
import com.faraz.app.moneytap.data_manager.api.Page;
import com.faraz.app.moneytap.data_manager.api.Result;
import com.faraz.app.moneytap.data_manager.api.SearchQuery;
import com.faraz.app.moneytap.data_manager.db.MoneyTapDB;
import com.faraz.app.moneytap.data_manager.db.PageDao;
import com.faraz.app.moneytap.data_manager.db.SearchDao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import retrofit2.Response;

/**
 * Created by root on 2/9/18.
 */

public class MainRepo {

    private PageDao pageDao;
    private SearchDao searchDao;
    private AppRxSchedulers appRxSchedulers;
    private ApiInterface apiInterface;
    private MoneyTapDB moneyTapDB;
    //current time
    private Long currentTime = Calendar.getInstance().getTimeInMillis();

    //ttl is set to 24 hours, after that it'll load from network and save the new data
    private int ttl = 24 * 60 * 60 * 1000;

    public MainRepo(PageDao pageDao, SearchDao searchDao, AppRxSchedulers appRxSchedulers, ApiInterface apiInterface, MoneyTapDB moneyTapDB) {
        this.pageDao = pageDao;
        this.searchDao = searchDao;
        this.appRxSchedulers = appRxSchedulers;
        this.apiInterface = apiInterface;
        this.moneyTapDB = moneyTapDB;
    }

    public Observable<ArrayList<String>> getAllPreviousSearches() {
        return searchDao.getAllQueries().map(result -> {
            ArrayList<String> stringList = new ArrayList<>();
            for (SearchQuery searchQuery: result)
                stringList.add(searchQuery.getSearch());

            return stringList;

        }).toObservable()
                .subscribeOn(appRxSchedulers.getIo())
                .observeOn(appRxSchedulers.getMain());
    }

    public Observable<Resource> getSearchResults(String search,String actualSearch) {
        return  Observable.concat(pageDao.getAllPages(search).toObservable()
                .onErrorResumeNext(throwable ->{
                    throwable.printStackTrace();
                    return Observable.just(new ArrayList<Page>()); })
                //convert the result from list to resource type
                .map(results -> { return  new Resource(Resource.Source.Database,results); })
                .subscribeOn(appRxSchedulers.getIo())
                //get from network
                ,searchForWords(search,actualSearch))

                //this will filter out the result which is empty or stale(older than 24 hrs)
                .filter(result ->
                        !result.getData().isEmpty() && (currentTime - result.getData().get(0).getTimeStamp()) < ttl)

                .first(new Resource(Resource.Source.Network,Collections.emptyList()))
                .toObservable()
                .observeOn(appRxSchedulers.getMain());


    }


    private Observable<Resource> searchForWords(String search, String actualSearch) {
        return apiInterface.getResult(search)
                //when the network call is completed save the query and result in db with current timestamp
                .doAfterNext(resultResponse -> {
                    if(resultResponse.body() != null) {

                        List<Page> pages = resultResponse.body().getQuery().getPages();
                        moneyTapDB.runInTransaction(() -> {
                            searchDao.insertSearch(new SearchQuery(actualSearch));
                            for (Page page:pages) {
                                page.setTimeStamp(currentTime);
                                pageDao.insertAll(page);
                            }
                        });

//                        pageDao.insertAll(.);
                    }
                })
                //convert the result to resource type
                .map(resultResponse -> {
                    if (resultResponse.body().getQuery() == null)
                       return new Resource(Resource.Source.Network,new ArrayList<Page>());
                    else
                    {
                        List<Page> pages = resultResponse.body().getQuery().getPages();
                        for (Page page:pages) {
                            page.setTimeStamp(currentTime);
                        }
                        return new Resource(Resource.Source.Network,pages);
                    }

                } ).subscribeOn(appRxSchedulers.getIo());
    }


}
