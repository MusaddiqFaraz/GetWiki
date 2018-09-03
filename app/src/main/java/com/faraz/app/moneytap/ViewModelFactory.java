package com.faraz.app.moneytap;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.faraz.app.moneytap.data_manager.AppRxSchedulers;
import com.faraz.app.moneytap.data_manager.MainRepo;
import com.faraz.app.moneytap.data_manager.api.ApiInterface;
import com.faraz.app.moneytap.data_manager.db.PageDao;
import com.faraz.app.moneytap.data_manager.db.SearchDao;
import com.faraz.app.moneytap.ui.MainVM;

/**
 * Created by root on 2/9/18.
 */

public class ViewModelFactory implements ViewModelProvider.Factory {


    private MainRepo mainRepo;

    public ViewModelFactory(MainRepo mainRepo) {
        this.mainRepo = mainRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainVM.class)) {
            return (T) new MainVM(mainRepo);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
