package com.faraz.app.moneytap.data_manager.api;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.*;
import retrofit2.http.Query;

import static com.faraz.app.moneytap.data_manager.api.RetrofitFactory.get;

/**
 * Created by root on 2/9/18.
 */

public interface ApiInterface {


    @GET(get)
    Observable<Response<Result>> getResult(@Query(RetrofitFactory.query) String search);

}
