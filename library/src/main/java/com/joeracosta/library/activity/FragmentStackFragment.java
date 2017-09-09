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

    private static final String BACKSTAG_FRAG_TAGS = "com.joeracosta.back_stack_frag_tags_fragment";

    FragmentManager mChildFragmentManager;
    SimpleFragment mCurrentFragment;
    Stack<String> mBackstackTags = new Stack<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChildFragmentManager = getChildFragmentManager();
        if (savedInstanceState != null && savedInstanceState.getSerializable(BACKSTAG_FRAG_TAGS) != null){
            mBackstackTags = (Stack<String>) savedInstanceState.getSerializable(BACKSTAG_FRAG_TAGS);
            if (!mBackstackTags.isEmpty()){
                mCurrentFragment = (SimpleFragment) mChildFragmentManager.findFragmentByTag(mBackstackTags.peek());
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(BACKSTAG_FRAG_TAGS, mBackstackTags);
        super.onSaveInstanceState(outState);
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
            mBackstackTags.pop();
            if (!mBackstackTags.isEmpty()) {
                mChildFragmentManager.popBackStackImmediate();
                mCurrentFragment = (SimpleFragment) mChildFragmentManager.findFragmentByTag(mBackstackTags.peek());
                return true;
            }
        }

        return false;
    }
}
