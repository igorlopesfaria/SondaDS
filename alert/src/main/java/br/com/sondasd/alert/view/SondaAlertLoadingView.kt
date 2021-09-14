package br.com.sondasd.alert.view

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.LayerDrawable
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.sondasd.alert.R
import br.com.sondasd.alert.databinding.SondaLoadingAlertBinding
import br.com.sondasd.core.extentions.themeAttr

class SondaAlertLoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?=null,
    defStyleAttr: Int = 0,
    message: String,
    icon: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding: SondaLoadingAlertBinding

    init {
        context.setTheme(context.themeAttr(R.attr.sondaAlertLoadingTheme).resourceId)

        binding = SondaLoadingAlertBinding.inflate(LayoutInflater.from(ContextThemeWrapper(context, R.style.Sonda_AlertThemeLoading)), this, true)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SondaLoadingAlertView,
            defStyleAttr,
            0
        ).apply {
            getColor(R.styleable.SondaLoadingAlertView_sondaAlertLoadingColor, 0).takeIf { it != 0 }
                ?.let {
                    (binding.progressHorizontal.progressDrawable as LayerDrawable).findDrawableByLayerId(
                        android.R.id.progress
                    ).colorFilter = PorterDuffColorFilter(it, PorterDuff.Mode.SRC_IN)
                    binding.progressHorizontal.indeterminateDrawable.colorFilter =
                        PorterDuffColorFilter(it, PorterDuff.Mode.SRC_IN)
                }
            getColor(
                R.styleable.SondaLoadingAlertView_sondaAlertLoadingBackgroundColor,
                0
            ).takeIf { it != 0 }?.let {
                (binding.progressHorizontal.progressDrawable as LayerDrawable).findDrawableByLayerId(
                    android.R.id.background
                ).colorFilter = PorterDuffColorFilter(it, PorterDuff.Mode.SRC_IN)
            }
        }
        binding.alertText.text = message
        binding.alertIcon.setImageResource(icon)
        binding.progressHorizontal.isIndeterminate = true

    }
}
