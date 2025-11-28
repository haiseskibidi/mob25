package ru.fefu.heeello

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        // Настройка выпадающего списка для выбора пола
        setupGenderDropdown()

        // Настройка кнопки назад
        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            onBackPressed()
        }

        // Настройка кнопки продолжить
        val btnContinue = findViewById<Button>(R.id.btnContinue)
        btnContinue.setOnClickListener {
            // Здесь будет логика проверки и регистрации
            // Пока просто переходим на экран входа
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Настройка кликабельных текстовых ссылок
        val tvPrivacyPolicy = findViewById<TextView>(R.id.tvPrivacyPolicy)
        tvPrivacyPolicy.setOnClickListener {
            // Здесь будет переход на политику конфиденциальности
        }

        val tvTermsOfUse = findViewById<TextView>(R.id.tvTermsOfUse)
        tvTermsOfUse.setOnClickListener {
            // Здесь будет переход на пользовательское соглашение
        }
    }

    private fun setupGenderDropdown() {
        val genders = arrayOf("Мужской", "Женский", "Другой")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, genders)
        val actvGender = findViewById<AutoCompleteTextView>(R.id.actvGender)
        actvGender.setAdapter(adapter)
    }
} 