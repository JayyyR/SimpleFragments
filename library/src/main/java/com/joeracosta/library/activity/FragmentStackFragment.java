package com.joeracosta.library.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Joe on 8/14/2017.
 */

public abstract class FragmentStackFragment extends SimpleFragment {

    FragmentManager mChildFragmentManager;
    SimpleFragment mCurrentFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChildFragmentManager = getChildFragmentManager();
    }

    public void addFragmentToStack(SimpleFragment fragmentToAdd, int fragmentContainerId,
                                      @Nullable String tag, @Nullable String backstackTag){

        mCurrentFragment = fragmentToAdd;
        FragmentTransaction fragmentTransaction = mChildFragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainerId, fragmentToAdd, tag);
        fragmentTransaction.addToBackStack(backstackTag);
        fragmentTransaction.commitAllowingStateLoss();
    }


    @Override
    public boolean onSimpleBackPressed() {
        if (mCurrentFragment.onSimpleBackPressed()){
            return true;
        }
        if (mChildFragmentManager.getBackStackEntryCount() > 0){
            mChildFragmentManager.popBackStackImmediate();
            //todo update current fragment
            return true;
        }

        return false;
    }
}
