package br.com.concretesolutions.kappuccino.custom.link

import android.support.annotation.IdRes

fun openLink(@IdRes linkViewId: Int, func: LinkInteractionsMethods.() -> LinkInteractionsMethods) = LinkInteractionsMethods(linkViewId).apply { func() }