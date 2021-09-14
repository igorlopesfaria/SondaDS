package br.com.sondasd.core.extentions

import android.content.Context
import android.util.TypedValue

fun Context.themeAttr(attrId : Int): TypedValue {
    val type = TypedValue()
    this.theme.resolveAttribute(attrId, type, true)
    return type
}