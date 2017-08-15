package com.joeracosta.simplefragments.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joeracosta.library.activity.FragmentStackFragment;
import com.joeracosta.simplefragments.R;

/**
 * Created by Joe on 8/14/2017.
 */

public class BlueStackFragment extends FragmentStackFragment {

    private int stackLevel;

    public static BlueStackFragment newInstance(int stackLevel){
        Bundle args = new Bundle();
        args.putInt(SampleMapActivity.STACK_LEVEL_KEY, stackLevel);
        BlueStackFragment fragment = new BlueStackFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

        if (args != null){
            stackLevel = args.getInt(SampleMapActivity.STACK_LEVEL_KEY);
        }

        addFragmentToStack(BlueFragment.newInstance(stackLevel), R.id.full_container, null, null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.stack_fragment, container, false);
    }

    @Override
    public void onShown() {
        System.out.print("");
    }

    @Override
    public void onHidden() {
        System.out.print("");
    }
}
