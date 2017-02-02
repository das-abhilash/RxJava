package com.example.abhilash.rxjava.EventBus;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Abhilash on 1/20/2017.
 */

public class FragmentTransactionObservable {

    private static FragmentTransactionObservable instance;
    private String toFragment = "";
    private String data;

    private PublishSubject<String> event = PublishSubject.create();

    public static FragmentTransactionObservable getInstance() {
        if (instance == null) {
            instance = new FragmentTransactionObservable();
        }
        return instance;
    }

    /**
     * Pass a fragemnt down to event listeners.
     */
    public void setToFragment (String toFragment) {
        event.onNext(toFragment);
    }

    public String getToFragment() {
        return toFragment;
    }


    public void setData(String data) {
        this.data = data;
    }

    public String getUser() {
        return data;
    }


    /**
     * Subscribe to this Observable. On event, do something e.g. replace a fragment
     */
    public Observable<String> getEvents() {
        return event;
    }
}
