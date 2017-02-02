package com.example.abhilash.rxjava.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.abhilash.rxjava.EventBus.FragmentTransactionObservable;
import com.example.abhilash.rxjava.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {


    public static final String TO = "ThirdFragment";

    public ThirdFragment() {
        // Required empty public constructor
    }


    List<Integer> list ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_third, container, false);
        list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));


        Observable.from(list)

                .flatMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer i) {
                        System.out.println(Thread.currentThread().getName() + "glatmap");
                        return Observable.just(i +1) ;
                    }
                })
                .flatMap(i -> Observable.just(i -1))
//                .subscribeOn(Schedulers.computation())
                .concatMap(i -> Observable.just(i -1))
                .filter(i -> i%2 ==0 )
                .take(5)
                .takeLast(4)
                .doOnNext(i -> { i++;
                    System.out.println(Thread.currentThread().getName());})
                .map(i -> i+1)
                .toList()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(i -> {showToast(i);
                    System.out.println(Thread.currentThread().getName());})
                .count()
//                .observeOn(AndroidSchedulers.mainThread())
        .subscribe(i -> showCount(i),
                error ->  showError(error.toString()));

        Observable<Integer> Obs1 = Observable.just(1);
        Observable<Integer> Obs2 = Observable.just(3);

        Observable.zip(Obs1, Obs2,

                new Func2<Integer, Integer, String >() {

                    @Override
                    public String call(Integer int1,
                                             Integer int2) {

                        return String.valueOf(int1) + " : " + String.valueOf(int2);
                    }
                }
        )
        .subscribe(s -> showError(s));


        return rootView;
    }

    private void showError(String  error) {
//        Toast.makeText(getContext(), error.toString() , Toast.LENGTH_SHORT).show();
        System.out.println(error);
    }

    private void showCount(Integer i) {
//        Toast.makeText(getContext(), String.valueOf(i) , Toast.LENGTH_SHORT).show();
        System.out.println(i);
    }

    private void showToast(List<Integer> list) {

        System.out.println("Start of the list");

        for ( int i = 0; i < list.size() ; i++ ) {
            showCount(list.get(i));
        }
        System.out.println("End of list");
    }


}
