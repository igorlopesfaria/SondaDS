package br.com.sondasd.alert.annotation

import androidx.annotation.RestrictTo

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class Status(val value: Type = Type.INFO) {
    enum class Type { ERROR, INFO, SUCCESS, WARNING }
}

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class Gravity(val value: Type = Type.TOP) {
    enum class Type { TOP, BOTTOM }
}