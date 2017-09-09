package com.joeracosta.samplefragmentapp.simple;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joeracosta.library.activity.SimpleFragment;
import com.joeracosta.samplefragmentapp.R;

/**
 * Created by Joe on 8/14/2017.
 */

public class GreenFragment extends SimpleFragment {

    public static GreenFragment newInstance(){
        return new GreenFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.green_fragment, container, false);
    }

    @Override
    public void onShown() {
        super.onShown();
        Log.d("visibility", "green fragment shown");
    }

    @Override
    public void onHidden() {
        super.onHidden();
        Log.d("visibility", "green fragment hidden");
    }
}
