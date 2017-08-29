package com.joeracosta.library.activity;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Joe on 8/14/2017.
 */

public abstract class FragmentStackActivity extends LifecycleActivity {

    FragmentManager mFragmentManager;
    SimpleFragment mCurrentFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();
    }

    public void addFragmentToStack(SimpleFragment fragmentToAdd, int fragmentContainerId,
                                      @Nullable String tag, @Nullable String backstackTag){

        mCurrentFragment = fragmentToAdd;
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainerId, fragmentToAdd, tag);
        fragmentTransaction.addToBackStack(backstackTag);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        if (mCurrentFragment.onSimpleBackPressed()){
            return;
        }
        if (mFragmentManager.getBackStackEntryCount() > 0){
             mFragmentManager.popBackStackImmediate();
            //todo update current fragment
        } else {
            super.onBackPressed();
        }
    }
}
