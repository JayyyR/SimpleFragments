package com.joeracosta.library.activity;

import android.arch.lifecycle.LifecycleFragment;

/**
 * Created by Joe on 8/14/2017.
 * Simple Fragment. Must be used in a FragmentStackFragment/Activity or FragmentMapFragment/Activity
 */

public abstract class SimpleFragment extends LifecycleFragment {

    private boolean atForefront;

    public void setAtForefront(boolean atForefront){
        this.atForefront = atForefront;
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
        System.out.print("");
    }

    /**
     * Called when the fragment is hidden on the screen
     */
    public void onHidden(){
        System.out.print("");
    }

    /**
     * Called when back is pressed when using this Fragment in a FragmentMapActivity or FragmentStackActivity
     * @return whether or not the backpress was handled
     */
    public boolean onSimpleBackPressed(){
        return false;
    }
}
