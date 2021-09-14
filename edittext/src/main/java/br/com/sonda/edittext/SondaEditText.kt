package br.com.sonda.edittext

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText

class SondaEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = com.google.android.material.R.attr.editTextStyle
) : TextInputEditText(context, attrs, defStyleAttr)
