package br.com.sondasd.infoview

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import androidx.annotation.ColorInt
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.DrawableCompat
import br.com.sondasd.core.extentions.hide
import br.com.sondasd.core.extentions.show
import br.com.sondasd.core.extentions.themeAttr
import br.com.sondasd.infoview.databinding.SondaInfoviewItemBinding

class SondaInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private var binding: SondaInfoviewItemBinding

    init {
        context.setTheme(context.themeAttr(R.attr.sondaInfoViewTheme).resourceId)

        binding = SondaInfoviewItemBinding.inflate(LayoutInflater.from(ContextThemeWrapper(context, R.style.Sonda_InfoViewTheme)), this, true)

        context.theme.obtainStyledAttributes(
            attrs, R.styleable.SondaInfoView, defStyle, 0
        ).apply {
            title = getString(R.styleable.SondaInfoView_infoTitle)
            message = getString(R.styleable.SondaInfoView_infoMessage)
            icon = getDrawable(R.styleable.SondaInfoView_infoIcon)
            val iconTint = getColor(R.styleable.SondaInfoView_infoIconTint, 0)
            changeIconColor(iconTint)

        }.recycle()
    }

    var title: String? = ""
        set(value) {
            if (!value.isNullOrEmpty()) {
                field = value
                binding.tvTitle.text = value
            }else {
                binding.tvTitle.hide()
            }
        }

    var message: String? = ""
        set(value) {
            field = value
            if (!value.isNullOrEmpty()) {
                binding.tvMessage.show()
                binding.tvMessage.text = message
            } else {
                binding.tvMessage.hide()
            }
        }

    var icon: Drawable? = null
        set(value) {
            field = value
            binding.ivIcon.setImageDrawable(icon)
        }

    private fun changeIconColor(@ColorInt iconTint: Int) {
        if (iconTint != 0) {
            binding.ivIcon.drawable?.let { drawable ->
                binding.ivIcon.setImageDrawable(DrawableCompat.wrap(drawable.mutate()))
                DrawableCompat.setTint(binding.ivIcon.drawable, iconTint)
            }
        }
    }

}