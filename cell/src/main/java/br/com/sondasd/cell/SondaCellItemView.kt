package br.com.sondasd.cell

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.util.Log.INFO
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import br.com.sondasd.core.extentions.hide
import br.com.sondasd.core.extentions.show
import br.com.sondasd.typography.SondaTextView
import com.google.android.material.card.MaterialCardView

class SondaCellItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.sondaCellItemTheme,
    @LayoutRes val normalLayoutRes: Int = R.layout.sonda_cell_item_view,
    @LayoutRes val skeletonLayoutRes: Int = R.layout.sonda_cell_item_skeleton,
) : MaterialCardView(context, attrs, defStyleAttr), StateView {

    override lateinit var currentView: View
    private lateinit var titleTextView: SondaTextView
    private lateinit var subtitleTextView: SondaTextView
    private lateinit var lineView: View
    private lateinit var iconView: AppCompatImageView
    private lateinit var containerView: ConstraintLayout

    private lateinit var titleSkeletonTextView: View
    private lateinit var subtitleSkeletonTextView: View
    private lateinit var lineSkeletonView: View
    private lateinit var iconSkeletonView: View

    override var currentState: StateView.State = StateView.State.NORMAL
        set(value) {
            field = value
            changeViewState()
        }

    init {
        this.currentState = StateView.State.NORMAL
        findViews()
        context.theme.obtainStyledAttributes(attrs, R.styleable.SondaCellItemView, defStyleAttr, 0)
            .apply {
                getString(R.styleable.SondaCellItemView_title)?.let { title = it }
                getString(R.styleable.SondaCellItemView_subtitle)?.let { subtitle = it }
                getResourceId(R.styleable.SondaCellItemView_iconSrc, 0).takeUnless { it == 0 }?.let { iconResource = it }
                getResourceId(R.styleable.SondaCellItemView_sondaCellBackground, 0).takeUnless { it == 0 }?.let { containerView.setBackgroundResource(it) }
                getColor(R.styleable.SondaCellItemView_sondaCellLineColor, 0).takeUnless { it == 0 }?.let { lineTint = it }
                getColor(R.styleable.SondaCellItemView_iconTint, 0).takeUnless { it == 0 }?.let { iconTint = it }
            }
    }

    private fun findViews() {
        when(currentState){
            StateView.State.SKELETON ->{
                titleSkeletonTextView = currentView.findViewById(R.id.sonda_cell_item_title)
                subtitleSkeletonTextView = currentView.findViewById(R.id.sonda_cell_item_subtitle)
                lineSkeletonView = currentView.findViewById(R.id.sonda_cell_line)
                iconSkeletonView = currentView.findViewById(R.id.sonda_cell_icon)

            }
            else -> {
                titleTextView = currentView.findViewById(R.id.sonda_cell_item_title)
                subtitleTextView = currentView.findViewById(R.id.sonda_cell_item_subtitle)
                lineView = currentView.findViewById(R.id.sonda_cell_line)
                iconView = currentView.findViewById(R.id.sonda_cell_icon)
                containerView = currentView.findViewById(R.id.sonda_cell_container)
            }
        }
    }


    override fun changeViewState() {
        currentView = when(currentState){
            StateView.State.SKELETON ->{
                removeAllViews()
                LayoutInflater.from(context).inflate(skeletonLayoutRes, this)
            }
            else -> {
                removeAllViews()
                LayoutInflater.from(context).inflate(normalLayoutRes, this)
            }
        }
        findViews()
    }

    var title: String? = null
        set(value) {
            field = value
            when (currentState) {
                StateView.State.SKELETON -> {
                   titleSkeletonTextView.show()
                }
                else -> {
                    titleTextView.text = value
                    titleTextView.show()
                }
            }
        }

    var subtitle: String? = null
        set(value) {
            field = value
            when (currentState) {
                StateView.State.SKELETON -> {
                    subtitleSkeletonTextView.show()
                }
                else -> {
                    subtitleTextView.text = value
                    subtitleTextView.show()
                }
            }
        }
    @ColorInt
    var iconTint: Int = ContextCompat.getColor(context, R.color.sonda_color_primary)
        set(value) {
            field = value
            iconView.setColorFilter(field)
        }

    @DrawableRes
    var iconResource: Int = 0
        set(value) {
            field = value
            when (currentState) {
                StateView.State.SKELETON -> {
                    iconSkeletonView.show()
                }
                else -> {
                    iconView.setColorFilter(iconTint)
                    resizeImg(R.dimen.sonda_cell_icon_size)
                    iconView.setImageResource(value)
                    if (value > 0) iconView.show() else iconView.hide()
                }
            }
        }

    @ColorInt
    var lineTint: Int = ContextCompat.getColor(context, R.color.sonda_neutral_dark)
        set(value) {
            field = value
            when (currentState) {
                StateView.State.SKELETON -> {
                    lineSkeletonView.show()
                }
                else -> {
                    lineView.setBackgroundColor(field)
                    lineView.show()
                }
            }
        }

    private fun resizeImg(@DimenRes size: Int) {
        val layoutParams = iconView.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.matchConstraintMaxHeight = resources.getDimension(size).toInt()
        layoutParams.matchConstraintMaxWidth = resources.getDimension(size).toInt()

        iconView.layoutParams = layoutParams
    }


}
