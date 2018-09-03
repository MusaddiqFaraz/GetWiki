package com.faraz.app.moneytap.ui;

import android.arch.lifecycle.ViewModel;

import com.faraz.app.moneytap.data_manager.MainRepo;
import com.faraz.app.moneytap.data_manager.Resource;
import com.faraz.app.moneytap.data_manager.api.Page;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by root on 2/9/18.
 */

public class MainVM extends ViewModel {

    private MainRepo mainRepo;


    public MainVM(MainRepo mainRepo) {
        this.mainRepo = mainRepo;
    }

    Observable<Resource> getSearchResult(String search) {
        return mainRepo.getSearchResults("%" + search + "%", search);
    }


    Observable<ArrayList<String>> getAllSearches() {
        return mainRepo.getAllPreviousSearches();
    }
}
