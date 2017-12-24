package com.joeracosta.library.activity

import android.os.Bundle
import java.util.*

/**
 * Created by Joe on 8/14/2017.
 * Meant to be a shell stack fragment that has a stack of SimpleFragments. Back presses etc are handled for you. If there is only one fragment in this stack,
 * and you press back, instead of being popped, this fragment's parent will get the back press. So you shouldn't inflate a layout here that needs to be visible to the user.
 * There should always be at least one SimpleFragment in the stack
 */
abstract class FragmentStackFragment : SimpleFragment() {

    protected var mCurrentFragment: SimpleFragment? = null
        private set
    private var mBackstackTags = Stack<String>()

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.getSerializable(BACKSTACK_FRAG_TAGS)?.let {
            mBackstackTags = it as Stack<String>
            if (!mBackstackTags.isEmpty()) {
                mCurrentFragment = childFragmentManager.findFragmentByTag(mBackstackTags.peek()) as SimpleFragment
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(BACKSTACK_FRAG_TAGS, mBackstackTags)
        super.onSaveInstanceState(outState)
    }

    /**
     * Add a SimpleFragment to the stack
     * @param fragmentToAdd New Instance of the Fragment you want
     * @param fragmentContainerId container Id for the fragment
     * @param tag
     * @param backstackTag
     */
    fun addFragmentToStack(fragmentToAdd: SimpleFragment, fragmentContainerId: Int,
                           tag: String?, backstackTag: String?) {
        var tagToUse = tag

        if (tagToUse == null) {
            tagToUse = fragmentToAdd.hashCode().toString()
        }

        mCurrentFragment?.let {
            it.setAtForefront(false)
            it.onHidden()
        }

        mBackstackTags.add(tagToUse)
        mCurrentFragment = fragmentToAdd
        mCurrentFragment?.setAtForefront(true)

        val fragmentTransaction = childFragmentManager.beginTransaction().apply {
            replace(fragmentContainerId, fragmentToAdd, tagToUse)
            addToBackStack(backstackTag)
        }

        fragmentTransaction.commitAllowingStateLoss()
    }

    fun hasFragments(): Boolean {
        return childFragmentManager.backStackEntryCount > 0
    }

    override fun onSimpleBackPressed(): Boolean {
        return handleBackPress()
    }

    /**
     * Passes the back press to children fragments so they can consume it if they'd like,
     * otherwise pops the top fragment in this stack
     * @return whether or not the back press was consumed by a child fragment
     */
    protected fun handleBackPress() : Boolean {
        if (mCurrentFragment?.onSimpleBackPressed() ?: false) {
            return true
        }
        if (childFragmentManager.backStackEntryCount > 0) {
            mBackstackTags.pop()
            if (!mBackstackTags.isEmpty()) {
                childFragmentManager.popBackStackImmediate()
                mCurrentFragment = childFragmentManager.findFragmentByTag(mBackstackTags.peek()) as SimpleFragment
                mCurrentFragment?.setAtForefront(true)
                mCurrentFragment?.onShown()
                return true
            }
        }

        return false
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)

        mCurrentFragment?.let {
            if (hidden) {
                it.setAtForefront(false)
                it.onHidden()
            } else {
                it.setAtForefront(true)
                it.onShown()
            }
        }
    }
}

private const val BACKSTACK_FRAG_TAGS = "com.joeracosta.back_stack_frag_tags_fragment"
