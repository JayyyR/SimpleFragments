package com.joeracosta.library.activity;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.Stack;

/**
 * Created by Joe on 8/14/2017.
 */

public abstract class FragmentStackActivity extends LifecycleActivity {

    FragmentManager mFragmentManager;
    SimpleFragment mCurrentFragment;
    Stack<String> mBackstackTags = new Stack<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();
    }

    public void addFragmentToStack(SimpleFragment fragmentToAdd, int fragmentContainerId,
                                   @Nullable String tag, @Nullable String backstackTag) {

        if (tag == null) {
            tag = String.valueOf(fragmentToAdd.hashCode());
        }

        mBackstackTags.add(tag);
        mCurrentFragment = fragmentToAdd;
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainerId, fragmentToAdd, tag);
        fragmentTransaction.addToBackStack(backstackTag);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        if (mCurrentFragment.onSimpleBackPressed()) {
            return;
        }
        if (mFragmentManager.getBackStackEntryCount() > 0) {
            mFragmentManager.popBackStackImmediate();
            mBackstackTags.pop();
            mCurrentFragment = (SimpleFragment) mFragmentManager.findFragmentByTag(mBackstackTags.peek());
        } else {
            super.onBackPressed();
        }
    }
}
