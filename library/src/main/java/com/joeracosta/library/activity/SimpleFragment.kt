package com.joeracosta.library.activity

import android.os.Bundle
import android.support.v4.app.Fragment

/**
 * Created by Joe on 8/14/2017.
 * Simple Fragment. Must be used in a FragmentStackFragment/Activity or FragmentMapFragment/Activity
 */

abstract class SimpleFragment : Fragment() {

    private var atForefront: Boolean = false

    internal fun setAtForefront(atForefront: Boolean) {
        this.atForefront = atForefront
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        atForefront = savedInstanceState?.getBoolean(FOREFRONT_TAG, false) ?: false

        if (parentFragment != null) {
            if (parentFragment !is FragmentStackFragment && parentFragment !is FragmentMapFragment) {
                throw RuntimeException("A SimpleFragment must have a FragmentStackFragment/Activity or FragmentMapFragment/Activity as a parent!")
            }
        } else {
            if (activity !is FragmentMapActivity && activity !is FragmentStackActivity) {
                throw RuntimeException("A SimpleFragment must have a FragmentStackFragment/Activity or FragmentMapFragment/Activity as a parent!")
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(FOREFRONT_TAG, atForefront)
        super.onSaveInstanceState(outState)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)

        if (hidden) {
            atForefront = false
            onHidden()
        } else {
            atForefront = true
            onShown()
        }
    }

    override fun onStart() {
        super.onStart()

        if (atForefront) { //only use onStart for when app is coming back from background
            onShown()
        }
    }

    override fun onStop() {
        super.onStop()

        if (atForefront) { //only use onStop for when app is backgrounding
            onHidden()
        }
    }

    /**
     * Called when the fragment is shown on the screen
     */
    open fun onShown() {}

    /**
     * Called when the fragment is hidden on the screen
     */
    open fun onHidden() {}

    /**
     * Called when back is pressed when using this Fragment in a FragmentMapActivity or FragmentStackActivity
     * @return whether or not the backpress was handled
     */
    open fun onSimpleBackPressed(): Boolean {
        return false
    }
}

private const val FOREFRONT_TAG = "com.joeracosta.at_forefront_tag"
