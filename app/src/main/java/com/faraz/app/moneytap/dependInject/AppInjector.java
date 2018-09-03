package com.faraz.app.moneytap.dependInject;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.faraz.app.moneytap.MoneyTap;

import dagger.android.AndroidInjection;
import dagger.android.HasFragmentInjector;

/**
 * Created by root on 2/9/18.
 */

public class AppInjector {

    public static void init(MoneyTap moneyTap){

        moneyTap.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                handleInjection(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

    }

    private static void handleInjection(Activity activity) {
        if(activity instanceof HasFragmentInjector)
            AndroidInjection.inject(activity);
    }
}
