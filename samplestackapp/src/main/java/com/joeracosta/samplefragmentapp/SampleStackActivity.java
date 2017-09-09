package com.joeracosta.samplefragmentapp;

import android.os.Bundle;

import com.joeracosta.library.activity.FragmentStackActivity;

public class SampleStackActivity extends FragmentStackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_stack);
    }
}
