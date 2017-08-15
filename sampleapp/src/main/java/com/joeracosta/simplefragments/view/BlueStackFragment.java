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

    public static BlueStackFragment newInstance(){
        return new BlueStackFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.blue_stack_fragment, container, false);
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
