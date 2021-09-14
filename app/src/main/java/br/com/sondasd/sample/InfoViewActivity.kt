package br.com.sondasd.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.sondasd.databinding.ActivitySampleButtonBinding
import br.com.sondasd.databinding.ActivitySampleInfoviewBinding

class InfoViewActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySampleInfoviewBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySampleInfoviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}