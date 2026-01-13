package ru.fefu.heeello

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Находим кнопку регистрации и устанавливаем обработчик нажатия
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        btnRegister.setOnClickListener {
            // Переход на экран регистрации
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        // Находим текст для перехода на экран входа и устанавливаем обработчик нажатия
        val tvLoginQuestion = findViewById<TextView>(R.id.tvLoginQuestion)
        tvLoginQuestion.setOnClickListener {
            // Переход на экран входа
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
} 