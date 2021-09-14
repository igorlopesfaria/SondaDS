package br.com.sondasd.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.sondasd.databinding.ActivitySampleButtonBinding

class ButtonsActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySampleButtonBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySampleButtonBinding.inflate(layoutInflater)
        setListeners()
        setContentView(binding.root)
    }

    private fun setListeners() {
        binding.loadingPrimaryButton.setOnClickListener {
            binding.loadingPrimaryButton.isLoading = !binding.loadingPrimaryButton.isLoading
        }

        binding.loadingSecondaryButton.setOnClickListener {
            binding.loadingSecondaryButton.isLoading = !binding.loadingSecondaryButton.isLoading
        }

    }

}