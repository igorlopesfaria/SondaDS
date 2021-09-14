package br.com.sondasd.alert.view

import android.content.Context
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.widget.LinearLayout
import br.com.sondasd.alert.R
import br.com.sondasd.alert.annotation.Status
import br.com.sondasd.alert.databinding.SondaBasicAlertBinding

class SondaAlertBoxView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    @Status status: Status.Type = Status.Type.INFO,
    message: String? = null
) : LinearLayout(context, attrs, defStyleAttr) {

    private var binding: SondaBasicAlertBinding

    init {

        val style: Int = when (status) {
            Status.Type.ERROR ->{
                R.style.Sonda_AlertThemeError
            }
            Status.Type.SUCCESS ->{
                R.style.Sonda_AlertThemeSuccess
            }
            Status.Type.WARNING ->{
                R.style.Sonda_AlertThemeWarning
            }
            else -> {
                R.style.Sonda_AlertThemeInfo
            }
        }
        binding = SondaBasicAlertBinding.inflate(LayoutInflater.from(ContextThemeWrapper(context, style)), this, true)
        binding.alertText.text = message
    }

    fun setOnCloseClickListener(onClickListener: OnClickListener) {
        binding.alertCloseIcon.setOnClickListener(onClickListener)
    }
}

