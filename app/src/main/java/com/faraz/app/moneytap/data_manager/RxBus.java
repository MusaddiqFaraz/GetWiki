package com.faraz.app.moneytap.data_manager;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by root on 2/9/18.
 */

public class RxBus {

    private static final RxBus INSTANCE= new RxBus();

    public static RxBus getInstance(){
        return INSTANCE;
    }


    //stickyBus which displays only the latest event that was emitted
    private final Subject<Object> stickyBus= BehaviorSubject.create();

    public void sendSticky(Object event) {
        stickyBus.onNext(event);
    }

    public Observable<Object> observeSticky()  {
        return stickyBus;
    }
}
