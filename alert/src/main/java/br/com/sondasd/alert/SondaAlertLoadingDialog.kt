package br.com.sondasd.alert

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.fragment.app.DialogFragment
import br.com.sondasd.alert.annotation.Gravity
import java.time.Duration

class SondaAlertLoadingDialog : DialogFragment() {

    lateinit var alert: SondaAlert

    companion object {

        fun make(
            activity: Activity,
            message: String,
            @DrawableRes icon: Int,
            @Gravity gravity: Gravity.Type
       ): SondaAlertLoadingDialog {
            val dialog = SondaAlertLoadingDialog()
            dialog.alert = SondaAlert.makeLoading(
                activity = activity,
                message = message,
                icon = icon,
                gravity = gravity
            )
            return dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(
            STYLE_NORMAL,
            R.style.SondaOverlayAlert
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return with(FrameLayout(inflater.context)) {
            addView(alert.view)
            this
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        alert.show()
    }

    override fun dismiss() {
        super.dismiss()
        alert.dismiss()
    }

}