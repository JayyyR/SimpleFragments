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

public abstract class FragmentStackActivity extends LifecycleActivity {

    FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();
    }

    public void addFragmentToStack(Fragment fragmentToAdd, int fragmentContainerId,
                                      @Nullable String tag, @Nullable String backstackTag){

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainerId, fragmentToAdd, tag);
        fragmentTransaction.addToBackStack(backstackTag);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
