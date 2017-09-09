package com.joeracosta.library.activity;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Joe on 8/14/2017.
 * Meant to be a shell map that contains SimpleFragments.
 */
public abstract class FragmentMapActivity extends LifecycleActivity {

    private static final String CURRENT_FRAG_TAG = "com.joeracosta.current_frag_tag_activity_map";

    private FragmentManager mFragmentManager;
    private SimpleFragment mCurrentFragment;
    private String mCurrentFragmentTag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();

        if (savedInstanceState != null && savedInstanceState.getString(CURRENT_FRAG_TAG) != null){
            mCurrentFragmentTag = savedInstanceState.getString(CURRENT_FRAG_TAG);
            mCurrentFragment = (SimpleFragment) mFragmentManager.findFragmentByTag(mCurrentFragmentTag);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(CURRENT_FRAG_TAG, mCurrentFragmentTag);
        super.onSaveInstanceState(outState);
    }


    public boolean hasFragments(){
        return mFragmentManager.getFragments().size() > 0;
    }

    /**
     * Show a fragment in the map
     * @param fragmentToAdd New Instance of the Fragment you want
     * @param fragmentContainerId container Id for the fragment
     * @param tag tag of the fragment transaction. If you want to show the same fragment that's
     *            already added, just make sure the tag is correct and it won't use the new instance
     */
    public void showFragmentInMap(SimpleFragment fragmentToAdd, int fragmentContainerId,
                                     @NonNull String tag){

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        SimpleFragment existingFragment = (SimpleFragment) mFragmentManager.findFragmentByTag(tag);
        if (existingFragment != null){
            fragmentToAdd = existingFragment;
        }

        if (mCurrentFragment != null){
            fragmentTransaction.hide(mCurrentFragment);
        }

        mCurrentFragmentTag = tag;
        mCurrentFragment = fragmentToAdd;

        if (fragmentToAdd.isAdded()){
            fragmentTransaction.show(fragmentToAdd);
        } else {
            fragmentToAdd.setAtForefront(true);
            fragmentTransaction.add(fragmentContainerId, fragmentToAdd, tag);
        }

        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        if (!mCurrentFragment.onSimpleBackPressed()){
            super.onBackPressed();
        }
    }
}
