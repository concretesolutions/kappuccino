package br.com.concretesolutions.kappuccino.custom.link

import androidx.annotation.IdRes

fun openLink(@IdRes linkViewId: Int, func: LinkInteractionsMethods.() -> LinkInteractionsMethods) = LinkInteractionsMethods(linkViewId).apply { func() }