package com.joeracosta.library.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.Stack;

/**
 * Created by Joe on 8/14/2017.
 */

public abstract class FragmentStackFragment extends SimpleFragment {

    FragmentManager mChildFragmentManager;
    SimpleFragment mCurrentFragment;
    Stack<String> mBackstackTags = new Stack<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChildFragmentManager = getChildFragmentManager();
    }

    public void addFragmentToStack(SimpleFragment fragmentToAdd, int fragmentContainerId,
                                      @Nullable String tag, @Nullable String backstackTag){

        if (tag == null){
            tag = String.valueOf(fragmentToAdd.hashCode());
        }

        mBackstackTags.add(tag);
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
            mBackstackTags.pop();
            mCurrentFragment = (SimpleFragment) mChildFragmentManager.findFragmentByTag(mBackstackTags.peek());
            return true;
        }

        return false;
    }
}
