package br.com.sondasd.buttons

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

class SondaLoadingButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.sondaButtonTheme
) : SondaButton(context, attrs, defStyleAttr) {

    private var hiddenText: CharSequence? = null
    private var hiddenIcon: Drawable? = null
    private var hiddenMinWidth: Int? = null

    var isLoading = false
        set(value) {
            field = value
            setLoadingButton(field)
        }

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SondaLoadingButton,
            defStyleAttr, 0
        ).apply {
            isLoading = getBoolean(R.styleable.SondaLoadingButton_isLoading, false)
        }.recycle()

        setLoadingButton(isLoading)
    }

    private fun setLoadingButton(loading: Boolean) {
        if (loading) {
            hiddenIcon = icon
            hiddenText = text
            hiddenMinWidth = minWidth
            minWidth = width
            text = ""
            val drawable = CircularProgressDrawable(context)
            drawable.setStyle(CircularProgressDrawable.DEFAULT)
            drawable.setColorSchemeColors(ContextCompat.getColor(context, R.color.sonda_neutral_light))
            drawable.strokeWidth = 6f
            icon = drawable
            drawable.start()
        } else {
            hiddenText?.let { text = it }
            hiddenMinWidth?.let { minWidth = it }
            icon = hiddenIcon
        }
    }
}
