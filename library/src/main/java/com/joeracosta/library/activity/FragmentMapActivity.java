package com.joeracosta.library.activity;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Joe on 8/14/2017.
 */

public abstract class FragmentMapActivity extends LifecycleActivity {

    FragmentManager mFragmentManager;
    Fragment mCurrentFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();
    }

    /**
     * Show a fragment in the map
     * @param fragmentToAdd New Instance of the Fragment you want
     * @param fragmentContainerId container Id for the fragment
     * @param tag tag of the fragment transaction. If you want to show the same fragment that's
     *            already added, just make sure the tag is correct and it won't use the new instance
     */
    public void showFragmentInMap(Fragment fragmentToAdd, int fragmentContainerId,
                                     String tag){

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        Fragment existingFragment = mFragmentManager.findFragmentByTag(tag);
        if (existingFragment != null){
            fragmentToAdd = existingFragment;
        }

        if (mCurrentFragment != null){
            fragmentTransaction.hide(mCurrentFragment);
        }

        mCurrentFragment = fragmentToAdd;

        if (fragmentToAdd.isAdded()){
            fragmentTransaction.show(fragmentToAdd);
        } else {
            fragmentTransaction.add(fragmentContainerId, fragmentToAdd, tag);
        }

        fragmentTransaction.commitAllowingStateLoss();
    }
}
