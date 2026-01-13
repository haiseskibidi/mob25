package ru.fefu.heeello

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Настройка кнопки назад
        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            onBackPressed()
        }

        // Настройка кнопки входа
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            // Получаем введенные логин и пароль
            val etLogin = findViewById<TextInputEditText>(R.id.etLogin)
            val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
            
            val login = etLogin.text.toString().trim()
            val password = etPassword.text.toString().trim()
            
            // Проверка на пустые поля
            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            // Здесь будет логика аутентификации
            // Пока просто показываем сообщение об успешном входе
            Toast.makeText(this, "Вход выполнен успешно", Toast.LENGTH_SHORT).show()
        }
    }
} 