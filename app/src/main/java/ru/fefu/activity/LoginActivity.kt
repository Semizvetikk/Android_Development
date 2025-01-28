package ru.fefu.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity(R.layout.activity_login) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val imageView = findViewById<ImageView>(R.id.bicyclists)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, Activity::class.java)
            startActivity(intent)
            finish()
        }

        val loginInputEditText = findViewById<TextInputEditText>(R.id.loginInputEditText)
        val passwordInputEditText = findViewById<TextInputEditText>(R.id.passwordInputEditText)
        val loginButton = findViewById<MaterialButton>(R.id.loginButton)

        // Обработка нажатия на кнопку входа
        loginButton.setOnClickListener {
            val login = loginInputEditText.text.toString()
            val password = passwordInputEditText.text.toString()

            // Проверка заполнения полей
            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            } else {
                // Вход выполнен успешно
                Toast.makeText(this, "Вход выполнен", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ActivityEmptyState::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}