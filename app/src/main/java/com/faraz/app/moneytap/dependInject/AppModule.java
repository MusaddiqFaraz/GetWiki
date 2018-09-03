package com.faraz.app.moneytap.dependInject;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.faraz.app.moneytap.ViewModelFactory;
import com.faraz.app.moneytap.data_manager.AppRxSchedulers;
import com.faraz.app.moneytap.data_manager.MainRepo;
import com.faraz.app.moneytap.data_manager.api.ApiInterface;
import com.faraz.app.moneytap.data_manager.api.RetrofitFactory;
import com.faraz.app.moneytap.data_manager.db.MoneyTapDB;
import com.faraz.app.moneytap.data_manager.db.PageDao;
import com.faraz.app.moneytap.data_manager.db.SearchDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by root on 2/9/18.
 */

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    Context providesAppContext() {
        return context;
    }

    @Provides
    @Singleton
    AppRxSchedulers providesScheduler(){
        return new AppRxSchedulers(Schedulers.io(),Schedulers.computation(), AndroidSchedulers.mainThread());
    }

    @Singleton
    @Provides
    MoneyTapDB providesAppDB(Context context) {
        return Room.databaseBuilder(context,MoneyTapDB.class,"Money-DB").build();
    }

    @Singleton
    @Provides
    PageDao providesPageDao(MoneyTapDB moneyTapDB) {
        return moneyTapDB.getPageDao();
    }

    @Singleton
    @Provides
    SearchDao searchDao(MoneyTapDB moneyTapDB) { return moneyTapDB.getSearchDao(); }

    @Singleton
    @Provides
    ApiInterface providesNetworkModule(){
        return RetrofitFactory.getRetrofitClient(RetrofitFactory.BASE_URL).create(ApiInterface.class);
    }


    @Singleton
    @Provides
    MainRepo getMainRepo(AppRxSchedulers appRxSchedulers,PageDao pageDao,SearchDao searchDao,ApiInterface apiInterface,MoneyTapDB moneyTapDB){
        return new MainRepo(pageDao, searchDao, appRxSchedulers, apiInterface,moneyTapDB);
    }

    @Singleton
    @Provides
    ViewModelFactory getViewModelFactory(MainRepo mainRepo){
        return new ViewModelFactory(mainRepo);
    }
}
