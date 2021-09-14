package br.com.sondasd.alert

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import br.com.sondasd.alert.annotation.Gravity
import br.com.sondasd.alert.annotation.Status
import br.com.sondasd.alert.view.SondaAlertBoxView
import br.com.sondasd.alert.view.SondaAlertLoadingView
import com.google.android.material.snackbar.BaseTransientBottomBar

class SondaAlert(
    parent: ViewGroup,
    content: ViewGroup,
    @Gravity val gravity: Gravity.Type,
    private val durationTime: Long,
    callback: com.google.android.material.snackbar.ContentViewCallback
) : BaseTransientBottomBar<SondaAlert>(parent, content, callback) {

    init {
        getView().setBackgroundColor(ContextCompat.getColor(
                view.context,
                android.R.color.transparent
            )
        )
        getView().setPadding(0, 0, 0, 0)
        (getView().layoutParams as FrameLayout.LayoutParams).gravity =
            if (gravity == Gravity.Type.TOP) android.view.Gravity.TOP else android.view.Gravity.BOTTOM
        animationMode = ANIMATION_MODE_FADE
        duration = LENGTH_INDEFINITE
    }

    companion object {
        const val TIME_SHORT = 1500L
        const val TIME_LONG = 3500L

        fun make(
            activity: Activity,
            message: String,
            @Status status: Status.Type = Status.Type.INFO,
            @Gravity gravity: Gravity.Type = Gravity.Type.TOP,
            duration: Long = TIME_LONG,
        ): SondaAlert {
            val customView = SondaAlertBoxView(
                context = activity,
                status = status,
                message = message)

            val parent: ViewGroup =
                activity.findViewById(android.R.id.content) as ViewGroup

            val alert = SondaAlert(parent, customView, gravity, duration, emptyCallback())

            customView.setOnCloseClickListener { alert.dismiss() }

            return alert
        }

        fun makeLoading(
            activity: Activity,
            message: String,
            @DrawableRes icon: Int,
            @Gravity gravity: Gravity.Type = Gravity.Type.TOP,
            ): SondaAlert {

            val customView = SondaAlertLoadingView(
                activity,
                message = message,
                icon = icon
            )

            val parent: ViewGroup =
                activity.findViewById(android.R.id.content) as ViewGroup

            return SondaAlert(parent, customView, gravity, LENGTH_INDEFINITE.toLong(), emptyCallback())
        }

        private fun emptyCallback() = object : com.google.android.material.snackbar.ContentViewCallback {
            override fun animateContentOut(delay: Int, duration: Int) { }
            override fun animateContentIn(delay: Int, duration: Int) { }
        }
    }

    override fun show(){
        super.show()

        val animation = when(gravity){
            Gravity.Type.TOP -> R.anim.slide_in_from_top
            Gravity.Type.BOTTOM -> R.anim.slide_in_from_bottom
        }

        getView().startAnimation(AnimationUtils.loadAnimation(context, animation))
        if (durationTime > 0) {
            getView().postDelayed({ dismiss() }, durationTime)
        }
    }

    override fun dismiss(){
        val animation = when(gravity){
            Gravity.Type.TOP -> R.anim.slide_out_from_top
            Gravity.Type.BOTTOM -> R.anim.slide_out_from_bottom
        }

        val animationUtils = AnimationUtils.loadAnimation(context, animation)
        animationUtils.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationEnd(animation: Animation?) {
                getView().visibility = View.GONE
                super@SondaAlert.dismiss()
            }

            @SuppressWarnings("EmptyFunctionBlock")
            override fun onAnimationStart(animation: Animation?) {
            }

            @SuppressWarnings("EmptyFunctionBlock")
            override fun onAnimationRepeat(animation: Animation?) {
            }
        })
        getView().startAnimation(animationUtils)
    }
}