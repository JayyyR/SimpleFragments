package com.joeracosta.simplefragments.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.joeracosta.library.activity.FragmentMapActivity;
import com.joeracosta.simplefragments.R;
import com.joeracosta.simplefragments.databinding.SampleMapActivityBinding;

public class SampleMapActivity extends FragmentMapActivity {

    public static final String STACK_LEVEL_KEY = "STACK_LEVEL";

    private static final String BLUE_FRAGMENT_TAG = "PURPLE_TAG";
    private static final String GREEN_FRAGMENT_TAG = "GREEN_TAG";

    SampleMapActivityBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.sample_map_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);

        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mBinding.blueMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragmentInMap(BlueStackFragment.newInstance(1), R.id.fragment_container, BLUE_FRAGMENT_TAG);
            }
        });

        mBinding.greenMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragmentInMap(GreenStackFragment.newInstance(1), R.id.fragment_container, GREEN_FRAGMENT_TAG);
            }
        });

        //start at green
        //mBinding.greenMenu.performClick();
    }

}
