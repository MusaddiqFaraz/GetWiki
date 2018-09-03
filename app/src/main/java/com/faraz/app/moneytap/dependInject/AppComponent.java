package com.faraz.app.moneytap.dependInject;

import com.faraz.app.moneytap.MoneyTap;
import com.faraz.app.moneytap.ui.MainVM;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by root on 2/9/18.
 */

@Singleton
@Component(modules = {AppModule.class,ActivityModule.class, AndroidSupportInjectionModule.class})
public interface AppComponent {


    void inject(MoneyTap moneyTap);
}
