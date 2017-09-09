package com.joeracosta.library.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.Stack;

/**
 * Created by Joe on 8/14/2017.
 * Meant to be a shell stack fragment that has a stack of SimpleFragments. Back presses etc are handled for you. If there is only one fragment in this stack,
 * and you press back, instead of being popped, this fragment's parent will get the back press. So you shouldn't inflate a layout here that needs to be visible to the user.
 * There should always be at least one SimpleFragment in the stack
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

        if (mCurrentFragment != null){
            mCurrentFragment.setAtForefront(false);
            mCurrentFragment.onHidden();
        }
        mBackstackTags.add(tag);
        mCurrentFragment = fragmentToAdd;
        mCurrentFragment.setAtForefront(true);
        FragmentTransaction fragmentTransaction = mChildFragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainerId, fragmentToAdd, tag);
        fragmentTransaction.addToBackStack(backstackTag);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public boolean hasFragments(){
        return mChildFragmentManager.getBackStackEntryCount() > 0;
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
