package com.example.abhilash.rxjava.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.abhilash.rxjava.EventBus.FragmentTransactionObservable;
import com.example.abhilash.rxjava.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {


    public static final String TO = "FirstFragment";

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_first, container, false);
        // Inflate the layout for this fragment
        Button clickButton = (Button)rootView.findViewById(R.id.button);
        clickButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentTransactionObservable.getInstance().setData("foo");
                FragmentTransactionObservable.getInstance().setToFragment(SecondFragment.TO);
            }
        });
        return rootView;
    }

}
