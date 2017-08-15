package com.joeracosta.library.activity;

import android.arch.lifecycle.LifecycleFragment;

/**
 * Created by Joe on 8/14/2017.
 */

public abstract class SimpleFragment extends LifecycleFragment {

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (hidden){
            onHidden();
        } else {
            onShown();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        if (!isHidden()) {
            onShown();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if (!isHidden()) {
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
}
