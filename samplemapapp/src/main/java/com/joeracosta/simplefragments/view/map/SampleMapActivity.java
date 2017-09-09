package com.joeracosta.simplefragments.view.map;

import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.joeracosta.library.activity.FragmentMapActivity;
import com.joeracosta.simplefragments.R;
import com.joeracosta.simplefragments.view.stack.BlueStackFragment;
import com.joeracosta.simplefragments.view.stack.GreenStackFragment;
import com.joeracosta.simplefragments.view.stack.RedStackFragment;

public class SampleMapActivity extends FragmentMapActivity {

    public static final String STACK_LEVEL_KEY = "STACK_LEVEL";
    private static final String BLUE_FRAGMENT_TAG = "PURPLE_TAG";
    private static final String GREEN_FRAGMENT_TAG = "GREEN_TAG";
    private static final String RED_FRAGMENT_TAG = "RED_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sample_map_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);

        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        findViewById(R.id.blue_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragmentInMap(BlueStackFragment.newInstance(1), R.id.fragment_container, BLUE_FRAGMENT_TAG);
            }
        });

        findViewById(R.id.green_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragmentInMap(GreenStackFragment.newInstance(1), R.id.fragment_container, GREEN_FRAGMENT_TAG);
            }
        });

        findViewById(R.id.red_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragmentInMap(RedStackFragment.newInstance(1), R.id.fragment_container, RED_FRAGMENT_TAG);
            }
        });

        //if we haven't recreated a state that already had fragments, start at green
        if (!hasFragments()) {
            findViewById(R.id.green_menu).performClick();
        }
    }

}
