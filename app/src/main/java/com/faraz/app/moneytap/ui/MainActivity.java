package com.faraz.app.moneytap.ui;

import android.app.Fragment;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.faraz.app.moneytap.R;
import com.faraz.app.moneytap.ViewModelFactory;
import com.faraz.app.moneytap.data_manager.AppRxSchedulers;
import com.faraz.app.moneytap.data_manager.MainRepo;
import com.faraz.app.moneytap.data_manager.Resource;
import com.faraz.app.moneytap.data_manager.RxBus;
import com.faraz.app.moneytap.data_manager.api.Page;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements HasFragmentInjector {


    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    AppRxSchedulers appRxSchedulers;

    @Inject
    ViewModelFactory viewModelFactory;


    private MainVM mainVM;
    private SearchResultRecyclerAdapter resultRecyclerAdapter;

    AppCompatAutoCompleteTextView autoCompleteTextView;
    Button btnfetch;
    RecyclerView rvSearchResult;
    ProgressBar pbFetch;

    private Disposable clickDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        autoCompleteTextView = (AppCompatAutoCompleteTextView) findViewById(R.id.acSearch);
        btnfetch = (Button) findViewById(R.id.btnFetch);
        rvSearchResult = (RecyclerView) findViewById(R.id.rvSearchResult);
        pbFetch = (ProgressBar) findViewById(R.id.pbFetch);
        rvSearchResult.setLayoutManager(new LinearLayoutManager(this));

        mainVM = ViewModelProviders.of(this, viewModelFactory).get(MainVM.class);

        setSearchAdapter();

        initViews();
    }

    private void initViews() {
        btnfetch.setOnClickListener(view -> {
                    if (autoCompleteTextView.getText().length() > 1)
                        getSearch(autoCompleteTextView.getText().toString());
                }
        );

        autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
            String query = (String) parent.getItemAtPosition(position);
            getSearch(query);
        });
    }

    /**
     * get all previous searches and set it to adapter of autocompletetextview
     */
    private void setSearchAdapter() {
        mainVM.getAllSearches()
                .subscribe(new Observer<ArrayList<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<String> strings) {
                        Log.d("MainAct", "list obtained " + strings);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, strings);
                        autoCompleteTextView.setAdapter(adapter);

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void getSearch(String search) {
        showProgressBar(true);
        mainVM.getSearchResult(search)
                .subscribe(new Observer<Resource>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Resource result) {
                        showProgressBar(false);
                        Log.d("MainAct", "pages result " + result.getData());
                        setSearchAdapter();

                        Toast.makeText(MainActivity.this, "From " + result.getSource(), Toast.LENGTH_SHORT).show();

                        resultRecyclerAdapter = new SearchResultRecyclerAdapter(new ArrayList<>(result.getData()), MainActivity.this);
                        rvSearchResult.setAdapter(resultRecyclerAdapter);
                        resultRecyclerAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(Throwable e) {
                        showProgressBar(false);
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void showProgressBar(Boolean show) {
        if (show) {
            btnfetch.setText("");
            pbFetch.setVisibility(View.VISIBLE);
        } else {
            btnfetch.setText("Search");
            pbFetch.setVisibility(View.GONE);
        }

    }


    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return dispatchingAndroidInjector;
    }


}
