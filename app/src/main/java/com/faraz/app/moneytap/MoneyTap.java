package com.faraz.app.moneytap;

import android.app.Activity;
import android.app.Application;

import com.faraz.app.moneytap.dependInject.AppComponent;
import com.faraz.app.moneytap.dependInject.AppInjector;
import com.faraz.app.moneytap.dependInject.AppModule;
import com.faraz.app.moneytap.dependInject.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by root on 2/9/18.
 */

public class MoneyTap extends Application implements HasActivityInjector {


    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    public static AppComponent component;

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }



    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        component.inject(this);
        AppInjector.init(this);
    }
}
