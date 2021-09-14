package br.com.sonda.edittext

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout

class SondaTextInputLayout @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    private val defStyleAttr: Int = com.google.android.material.R.attr.textInputStyle
) : TextInputLayout(context, attrs, defStyleAttr) {
    override fun setError(errorText: CharSequence?) {
        this.setError(errorText?.toString())
    }

    private fun setError(message: String?) {
        if (message == null) {
            resetState()
        } else {
            setStateMessage(message, R.styleable.SondaInputLayout_sondaInputColorError, ContextCompat.getColor(context, R.color.sonda_color_error))
        }
    }
    fun resetState() {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SondaInputLayout,
            defStyleAttr, 0
        ).apply {

            val defaultColorRes = R.color.sonda_color_secondary
            val defaultColor = ContextCompat.getColor(context, defaultColorRes)

            getColorStateList(R.styleable.SondaInputLayout_sondaInputStrokeColor).let {
                boxStrokeColor = it?.getColorForState(View.FOCUSED_STATE_SET, defaultColorRes) ?: defaultColor
            }

            getColor(R.styleable.SondaInputLayout_sondaInputHintTextColor, defaultColor).let {
                hintTextColor = ColorStateList.valueOf(it)
                setHelperTextColor(ColorStateList.valueOf(it))
            }
            getColor(R.styleable.SondaInputLayout_sondaInputIconColor, defaultColor).let {
                setStartIconTintList(ColorStateList.valueOf(it))
            }
        }
        helperText = null
    }
    private fun setStateMessage(message: String?, helperColor: Int, defaultColor: Int) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SondaInputLayout,
            defStyleAttr, 0
        ).apply {
            getColor(helperColor, defaultColor).let {
                boxStrokeColor = it
                setHelperTextColor(ColorStateList.valueOf(it))
                hintTextColor = ColorStateList.valueOf(it)
                setStartIconTintList(ColorStateList.valueOf(it))
            }
        }

        helperText = message
    }


}