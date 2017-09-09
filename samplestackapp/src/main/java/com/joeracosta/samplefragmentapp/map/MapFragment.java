package com.joeracosta.samplefragmentapp.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joeracosta.library.activity.FragmentMapFragment;
import com.joeracosta.samplefragmentapp.R;
import com.joeracosta.samplefragmentapp.simple.BlueFragment;
import com.joeracosta.samplefragmentapp.simple.GreenFragment;
import com.joeracosta.samplefragmentapp.simple.RedFragment;

/**
 * Created by Joe on 9/9/2017.
 */

public class MapFragment extends FragmentMapFragment {

    private static final String BLUE_FRAGMENT_TAG = "PURPLE_TAG";
    private static final String GREEN_FRAGMENT_TAG = "GREEN_TAG";
    private static final String RED_FRAGMENT_TAG = "RED_TAG";

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment, container, false);

        view.findViewById(R.id.blue_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragmentInMap(BlueFragment.newInstance(), R.id.fragment_container, BLUE_FRAGMENT_TAG);
            }
        });

        view.findViewById(R.id.green_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragmentInMap(GreenFragment.newInstance(), R.id.fragment_container, GREEN_FRAGMENT_TAG);
            }
        });

        view.findViewById(R.id.red_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragmentInMap(RedFragment.newInstance(), R.id.fragment_container, RED_FRAGMENT_TAG);
            }
        });

        //if we haven't recreated a state that already had fragments, start at one green frag
        if (!hasFragments()) {
            view.findViewById(R.id.green_menu).performClick();
        }

        return view;
    }

}
