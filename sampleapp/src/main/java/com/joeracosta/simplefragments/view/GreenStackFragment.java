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

public class GreenStackFragment extends FragmentStackFragment {

    public static GreenStackFragment newInstance(){
        return new GreenStackFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.green_stack_fragment, container, false);
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
