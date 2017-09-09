package com.joeracosta.samplefragmentapp.simple;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.joeracosta.library.activity.FragmentStackActivity;
import com.joeracosta.library.activity.SimpleFragment;
import com.joeracosta.samplefragmentapp.R;
import com.joeracosta.samplefragmentapp.stack.SampleStackActivity;

/**
 * Created by Joe on 9/9/2017.
 */

public class PurpleFragment extends SimpleFragment {

    public static PurpleFragment newInstance(int stackLevel){
        Bundle args = new Bundle();
        args.putInt(SampleStackActivity.STACK_LEVEL_KEY, stackLevel);
        PurpleFragment fragment = new PurpleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private int stackLevel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();

        if (args != null){
            stackLevel = args.getInt(SampleStackActivity.STACK_LEVEL_KEY);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(SampleStackActivity.STACK_LEVEL_KEY, stackLevel);
        super.onSaveInstanceState(outState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.purple_fragment, container, false);
        ((TextView)view.findViewById(R.id.stack_num)).setText(Integer.toString(stackLevel));

        ((Button)view.findViewById(R.id.deeper_again)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((FragmentStackActivity) getActivity()).addFragmentToStack(PurpleFragment.newInstance(stackLevel+1), R.id.fragment_container, null, null);
            }
        });

        return view;
    }

    @Override
    public void onShown() {
        super.onShown();
        Log.d("visibility", "purple fragment " + stackLevel + " shown");
    }

    @Override
    public void onHidden() {
        super.onHidden();
        Log.d("visibility", "purple fragment " + stackLevel + " hidden");
    }
}
