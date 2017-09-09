package com.joeracosta.simplefragments.view.stack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joeracosta.library.activity.FragmentStackFragment;
import com.joeracosta.simplefragments.R;
import com.joeracosta.simplefragments.view.map.SampleMapActivity;
import com.joeracosta.simplefragments.view.simple.GreenFragment;

/**
 * Created by Joe on 8/14/2017.
 */

public class GreenStackFragment extends FragmentStackFragment {

    public static GreenStackFragment newInstance(int stackLevel){
        Bundle args = new Bundle();
        args.putInt(SampleMapActivity.STACK_LEVEL_KEY, stackLevel);
        GreenStackFragment fragment = new GreenStackFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private int stackLevel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();

        if (args != null){
            stackLevel = args.getInt(SampleMapActivity.STACK_LEVEL_KEY);
        }

        //if we haven't recreated a state that already had fragments, start at one green frag
        if (!hasFragments()) {
            addFragmentToStack(GreenFragment.newInstance(stackLevel), R.id.full_container, null, null);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(SampleMapActivity.STACK_LEVEL_KEY, stackLevel);
        super.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.stack_fragment, container, false);
    }
}
