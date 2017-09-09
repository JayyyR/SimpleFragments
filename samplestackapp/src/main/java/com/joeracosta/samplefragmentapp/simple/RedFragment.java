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

public class RedFragment extends SimpleFragment {

    public static RedFragment newInstance(){
        return new RedFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.red_fragment, container, false);
    }

    @Override
    public void onShown() {
        super.onShown();
        Log.d("visibility", "red fragment shown");
    }

    @Override
    public void onHidden() {
        super.onHidden();
        Log.d("visibility", "red fragment hidden");
    }
}
