package ru.fefu.heeello

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.fefu.heeello.databinding.ActivityChangePasswordBinding

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.applyButton.setOnClickListener {
            // Add logic to change password here
            finish()
        }
    }
} 