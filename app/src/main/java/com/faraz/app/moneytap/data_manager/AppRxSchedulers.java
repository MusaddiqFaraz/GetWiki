package com.faraz.app.moneytap.data_manager;

import io.reactivex.Scheduler;

/**
 * Created by root on 2/9/18.
 */

public class AppRxSchedulers {

    private Scheduler io;
    private Scheduler computation;
    private Scheduler main;

    public AppRxSchedulers(Scheduler io, Scheduler computation, Scheduler main) {
        this.io = io;
        this.computation = computation;
        this.main = main;
    }

    public Scheduler getIo() {
        return io;
    }

    public void setIo(Scheduler io) {
        this.io = io;
    }

    public Scheduler getComputation() {
        return computation;
    }

    public void setComputation(Scheduler computation) {
        this.computation = computation;
    }

    public Scheduler getMain() {
        return main;
    }

    public void setMain(Scheduler main) {
        this.main = main;
    }
}
