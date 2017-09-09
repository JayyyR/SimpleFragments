package com.joeracosta.samplefragmentapp.stack;

import android.os.Bundle;

import com.joeracosta.library.activity.FragmentStackActivity;
import com.joeracosta.samplefragmentapp.R;
import com.joeracosta.samplefragmentapp.simple.PurpleFragment;

public class SampleStackActivity extends FragmentStackActivity {

    public static final String STACK_LEVEL_KEY = "STACK_LEVEL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_stack);

        if (!hasFragments()){
            addFragmentToStack(PurpleFragment.newInstance(1), R.id.fragment_container, null, null);
        }
    }
}
