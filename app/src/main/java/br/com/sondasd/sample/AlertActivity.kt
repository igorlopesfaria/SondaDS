package br.com.sondasd.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.sondasd.alert.R
import br.com.sondasd.alert.SondaAlert
import br.com.sondasd.alert.SondaAlertLoadingDialog
import br.com.sondasd.alert.annotation.Gravity
import br.com.sondasd.alert.annotation.Status
import br.com.sondasd.databinding.ActivitySampleAlertBinding
import com.google.android.material.snackbar.BaseTransientBottomBar

class AlertActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySampleAlertBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleAlertBinding.inflate(layoutInflater)
        listeners()
        setContentView(binding.root)
    }

    private fun listeners() {
        binding.errorBottomButton.setOnClickListener {
            showAlert("Mensagem de Error", Status.Type.ERROR, Gravity.Type.BOTTOM)
        }

        binding.errorTopButton.setOnClickListener {
            showAlert("Mensagem de Error", Status.Type.ERROR, Gravity.Type.TOP)
        }

        binding.warningBottomButton.setOnClickListener {
            showAlert("Mensagem de Warning", Status.Type.WARNING, Gravity.Type.BOTTOM)
        }

        binding.warningTopButton.setOnClickListener {
            showAlert("Mensagem de Warning", Status.Type.WARNING, Gravity.Type.TOP)
        }

        binding.infoBottomButton.setOnClickListener {
            showAlert("Mensagem de Info", Status.Type.INFO, Gravity.Type.BOTTOM)
        }

        binding.infoTopButton.setOnClickListener {
            showAlert("Mensagem de Info", Status.Type.INFO, Gravity.Type.TOP)
        }

        binding.successBottomButton.setOnClickListener {
            showAlert("Mensagem de Success", Status.Type.SUCCESS, Gravity.Type.BOTTOM)
        }

        binding.successTopButton.setOnClickListener {
            showAlert("Mensagem de Success", Status.Type.SUCCESS, Gravity.Type.TOP)
        }

        binding.loadingTopButton.setOnClickListener {
            showLoadingAlert("Mensagem de Loading", Gravity.Type.TOP)
        }

        binding.loadingBottomButton.setOnClickListener {
            showLoadingAlert("Mensagem de Loading", Gravity.Type.BOTTOM)
        }

    }

    private fun showAlert(message: String, @Status status: Status.Type, @Gravity gravity: Gravity.Type) {
        SondaAlert.make(activity = this,
            message = message,
            gravity = gravity,
            status = status,
            duration = SondaAlert.TIME_LONG).show()
    }

    private fun showLoadingAlert(message: String, @Gravity gravity: Gravity.Type) {
        SondaAlertLoadingDialog.make(
            activity = this,
            message = message,
            icon = R.drawable.ic_timer,
            gravity = gravity
        ).show(supportFragmentManager, "ALERT")
    }

}