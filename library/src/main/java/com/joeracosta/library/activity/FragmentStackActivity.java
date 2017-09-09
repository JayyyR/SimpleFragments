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

    private static final String BACKSTAG_FRAG_TAGS = "com.joeracosta.back_stack_frag_tags_activity";

    FragmentManager mFragmentManager;
    SimpleFragment mCurrentFragment;
    Stack<String> mBackstackTags = new Stack<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();

        if (savedInstanceState != null && savedInstanceState.getSerializable(BACKSTAG_FRAG_TAGS) != null){
            mBackstackTags = (Stack<String>) savedInstanceState.getSerializable(BACKSTAG_FRAG_TAGS);
            if (!mBackstackTags.isEmpty()){
                mCurrentFragment = (SimpleFragment) mFragmentManager.findFragmentByTag(mBackstackTags.peek());
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(BACKSTAG_FRAG_TAGS, mBackstackTags);
        super.onSaveInstanceState(outState);
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
            mBackstackTags.pop();
            if (!mBackstackTags.isEmpty()) {
                mFragmentManager.popBackStackImmediate();
                mCurrentFragment = (SimpleFragment) mFragmentManager.findFragmentByTag(mBackstackTags.peek());
            } else {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }
}
