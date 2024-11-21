package com.example.partial;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment1 extends Fragment {

    TextView tv_string;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        tv_string = view.findViewById(R.id.tv_string);

        String dataReceived = getArguments().getString(MainActivity.KEY_ACTIVITY_TO_FRAGMENT);
        tv_string.setText(dataReceived);

        return view;
    }
}