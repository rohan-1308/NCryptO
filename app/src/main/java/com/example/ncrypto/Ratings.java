package com.example.ncrypto;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class Ratings extends Fragment {
    RatingBar ratingbar;
    Button rtngs;
    public Ratings() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ratings, container, false);
        rtngs = v.findViewById(R.id.rtngs);
        ratingbar = (RatingBar)v.findViewById(R.id.ratingBar);
        rtngs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rating=String.valueOf(ratingbar.getRating());
                Toast.makeText(getActivity(), "Thank You for Rating Us with "+rating+" stars.", Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent resrt = new Intent(getActivity(),MainActivity.class);
                        startActivity(resrt);
                    }
                }, 3000);
            }
        });
        return v;
    }
}