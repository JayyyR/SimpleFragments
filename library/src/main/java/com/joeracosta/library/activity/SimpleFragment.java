package com.joeracosta.library.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by Joe on 8/14/2017.
 * Simple Fragment. Must be used in a FragmentStackFragment/Activity or FragmentMapFragment/Activity
 */

public abstract class SimpleFragment extends Fragment {

    private static final String FOREFRONT_TAG = "com.joeracosta.at_forefront_tag";

    private boolean atForefront;

    public void setAtForefront(boolean atForefront){
        this.atForefront = atForefront;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null){
            atForefront = savedInstanceState.getBoolean(FOREFRONT_TAG, false);
        }

        if (getParentFragment() != null){
            if (!(getParentFragment() instanceof FragmentStackFragment) &&
                    !(getParentFragment() instanceof FragmentMapFragment)){
                throw new RuntimeException("A SimpleFragment must have a FragmentStackFragment/Activity or FragmentMapFragment/Activity as a parent!");
            }
        } else {
            if (!(getActivity() instanceof FragmentMapActivity) &&
                    !(getActivity() instanceof FragmentStackActivity)){
                throw new RuntimeException("A SimpleFragment must have a FragmentStackFragment/Activity or FragmentMapFragment/Activity as a parent!");
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(FOREFRONT_TAG, atForefront);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (hidden){
            atForefront = false;
            onHidden();
        } else {
            atForefront = true;
            onShown();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        if (atForefront) { //only use onStart for when app is coming back from background
            onShown();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if (atForefront) { //only use onStop for when app is backgrounding
            onHidden();
        }
    }

    /**
     * Called when the fragment is shown on the screen
     */
    public void onShown(){
    }

    /**
     * Called when the fragment is hidden on the screen
     */
    public void onHidden(){
    }

    /**
     * Called when back is pressed when using this Fragment in a FragmentMapActivity or FragmentStackActivity
     * @return whether or not the backpress was handled
     */
    public boolean onSimpleBackPressed(){
        return false;
    }
}
