package br.com.sondasd.buttons

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton

open class SondaButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.sondaButtonTheme
) : MaterialButton(context, attrs, defStyleAttr) {

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SondaButton,
            defStyleAttr, 0
        ).apply {

            getResourceId(R.styleable.SondaButton_sondaButtonBackground, 0).let { res ->
                takeIf { res != 0 }?.let {
                    setBackgroundResource(getResourceId(R.styleable.SondaButton_sondaButtonBackground, 0))
                }
            }
        }.recycle()
    }
}