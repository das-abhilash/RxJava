package com.example.abhilash.rxjava;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.abhilash.rxjava.EventBus.FragmentTransactionObservable;
import com.example.abhilash.rxjava.view.FirstFragment;
import com.example.abhilash.rxjava.view.SecondFragment;
import com.example.abhilash.rxjava.view.ThirdFragment;

import rx.Subscription;

public class MainActivity extends AppCompatActivity {

    FragmentTransactionObservable fragmentObs;
    Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new FirstFragment())
                .commit();

        fragmentObs = FragmentTransactionObservable.getInstance();
    }

    private void checkFromEvent(String toFragment) {
        Fragment fragment;
        switch (toFragment) {
            case SecondFragment.TO:
                fragment = new SecondFragment();
                break;
            case ThirdFragment.TO:

                fragment = new ThirdFragment();

                break;
            default:
                fragment = new FirstFragment();
                break;
        }


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, "SignUpFragment")
                .commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        subscription = fragmentObs.getEvents()
                .subscribe(from -> checkFromEvent(from));
    }

    @Override
    protected void onStop() {
        super.onStop();
        subscription.unsubscribe();
    }
}
