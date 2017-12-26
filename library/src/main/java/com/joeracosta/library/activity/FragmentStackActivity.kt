package com.joeracosta.library.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import java.util.*

/**
 * Created by Joe on 8/14/2017.
 * Meant to be a shell stack activity that has a stack of SimpleFragments. Back presses etc are handled for you. If there is only one fragment in this stack,
 * and you press back, instead of being popped, this activity will get the back press. So you shouldn't inflate a layout here inside the fragment container that is meant to be visible
 * to the user. There should always be at least one SimpleFragment in the stack.
 */
abstract class FragmentStackActivity : AppCompatActivity() {

    protected var mCurrentFragment: SimpleFragment? = null
        private set
    private var mBackstackTags = Stack<String>()

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState?.getSerializable(BACKSTACK_FRAG_TAGS)?.let {
            mBackstackTags = it as Stack<String>
            if (!mBackstackTags.isEmpty()) {
                mCurrentFragment = supportFragmentManager.findFragmentByTag(mBackstackTags.peek()) as SimpleFragment
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

        val fragmentTransaction = supportFragmentManager.beginTransaction().apply {
            replace(fragmentContainerId, fragmentToAdd, tagToUse)
            addToBackStack(backstackTag)
        }

        fragmentTransaction.commitAllowingStateLoss()
    }

    fun hasFragments(): Boolean {
        return supportFragmentManager.backStackEntryCount > 0
    }

    override fun onBackPressed() {
       if (!handleBackPress()) {
           super.onBackPressed()
       }
    }

    /**
     * Passes the back press to children fragments so they can consume it if they'd like,
     * otherwise pops the top fragment in this stack
     * @return whether or not the back press was consumed by a child fragment
     */
    protected fun handleBackPress() : Boolean{
        if (mCurrentFragment?.onSimpleBackPressed() ?: false) {
            return true;
        }
        if (supportFragmentManager.backStackEntryCount > 0) {
            mBackstackTags.pop()
            if (!mBackstackTags.isEmpty()) {
                supportFragmentManager.popBackStackImmediate()
                mCurrentFragment = supportFragmentManager.findFragmentByTag(mBackstackTags.peek()) as SimpleFragment
                mCurrentFragment?.setAtForefront(true)
                mCurrentFragment?.onShown()
            } else {
                finish() //have to call finish to finish the activity when there's one fragment left in the stack
            }
            return true;
        }
        return false;
    }
}


private const val BACKSTACK_FRAG_TAGS = "com.joeracosta.back_stack_frag_tags_activity"