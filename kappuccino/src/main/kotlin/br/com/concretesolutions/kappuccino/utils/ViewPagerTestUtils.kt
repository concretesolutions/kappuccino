package br.com.concretesolutions.kappuccino.utils

import android.support.test.espresso.ViewAssertion
import br.com.concretesolutions.kappuccino.counters.ViewPagerItemCountAssertion

object ViewPagerTestUtils {

    fun viewPagerHasSize(size: Int): ViewAssertion {
        return ViewPagerItemCountAssertion(size)
    }
}
