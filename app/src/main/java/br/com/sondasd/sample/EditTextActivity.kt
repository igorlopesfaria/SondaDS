package br.com.sondasd.sample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.sondasd.databinding.ActivitySampleTextinputBinding

class EditTextActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySampleTextinputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySampleTextinputBinding.inflate(layoutInflater)

        binding.sondaInputLayout.error = null
        binding.sondaInputLayout.isErrorEnabled = false;

        binding.error.setOnClickListener {
            if(binding.sondaEditText.text.isNullOrEmpty()) {

                binding.sondaInputLayout.error = "Campo obrigatório"

            }
        }
        binding.reset.setOnClickListener {
            binding.sondaInputLayout.resetState()
        }

        setContentView(binding.root)
    }
}