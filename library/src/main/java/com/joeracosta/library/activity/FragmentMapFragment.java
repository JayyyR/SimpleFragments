package com.joeracosta.library.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Joe on 8/14/2017.
 * Meant to be a shell map fragment that has a map of SimpleFragments. Back presses etc are handled for you.
 * The back presses will be sent to the SimpleFragment thats currently shown in the map and handled there. If it's not handled
 * It will be sent to this activity. You shouldn't inflate a layout here inside the fragment container that is meant to be visible
 * to the user.
 */
public abstract class FragmentMapFragment extends SimpleFragment {

    private static final String CURRENT_FRAG_TAG = "com.joeracosta.current_frag_tag_fragment_map";

    FragmentManager mChildFragmentManager;
    SimpleFragment mCurrentFragment;
    private String mCurrentFragmentTag;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChildFragmentManager = getChildFragmentManager();
        if (savedInstanceState != null && savedInstanceState.getString(CURRENT_FRAG_TAG) != null){
            mCurrentFragmentTag = savedInstanceState.getString(CURRENT_FRAG_TAG);
            mCurrentFragment = (SimpleFragment) mChildFragmentManager.findFragmentByTag(mCurrentFragmentTag);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(CURRENT_FRAG_TAG, mCurrentFragmentTag);
        super.onSaveInstanceState(outState);
    }


    public boolean hasFragments(){
        return mChildFragmentManager.getFragments().size() > 0;
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

        FragmentTransaction fragmentTransaction = mChildFragmentManager.beginTransaction();

        SimpleFragment existingFragment = (SimpleFragment) mChildFragmentManager.findFragmentByTag(tag);
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
    public boolean onSimpleBackPressed() {
        return mCurrentFragment.onSimpleBackPressed();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (mCurrentFragment != null){
            if (hidden){
                mCurrentFragment.setAtForefront(false);
                mCurrentFragment.onHidden();
            } else {
                mCurrentFragment.setAtForefront(true);
                mCurrentFragment.onShown();
            }
        }
    }

}
