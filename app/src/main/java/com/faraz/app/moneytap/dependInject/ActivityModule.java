package com.faraz.app.moneytap.dependInject;

import com.faraz.app.moneytap.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by root on 2/9/18.
 */

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract MainActivity getMainActivity();
}
