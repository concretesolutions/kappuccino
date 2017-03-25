package br.com.concretesolutions.kappuccino.assertions

import br.com.concretesolutions.kappuccino.utils.RecyclerViewTestUtils
import br.com.concretesolutions.kappuccino.utils.ViewPagerTestUtils
import br.com.concretesolutions.kappuccino.withId

class ListSizeAssertions {

    fun viewPager(viewId: Int, size: Int): ListSizeAssertions {
        withId(viewId).check(ViewPagerTestUtils.viewPagerHasSize(size))
        return this
    }

    fun recyclerView(viewId: Int, size: Int): ListSizeAssertions {
        withId(viewId).check(RecyclerViewTestUtils.recyclerViewHasSize(size))
        return this
    }
}

