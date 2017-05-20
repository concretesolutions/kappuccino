package br.com.concretesolutions.kappuccino

interface BaseMatcherMethods {
    fun id(viewId: Int): Any
    fun text(textId: Int): Any
    fun text(text: String): Any
    fun contentDescription(contentDescriptionId: Int): Any
    fun contentDescription(contentDescription: String): Any
    fun image(imageId: Int): Any
    fun textColor(colorId: Int): Any
    fun parent(func: BaseViewMatchers.() -> Unit): Any
    fun descendant(func: BaseViewMatchers.() -> Unit): Any
}