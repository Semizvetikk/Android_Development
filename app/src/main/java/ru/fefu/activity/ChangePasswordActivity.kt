package ru.fefu.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var oldPasswordInputEditText: TextInputEditText
    private lateinit var newPasswordInputEditText: TextInputEditText
    private lateinit var repeatPasswordInputEditText: TextInputEditText
    private lateinit var acceptButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        // Инициализация элементов интерфейса
        oldPasswordInputEditText = findViewById(R.id.oldPasswordInputEditText)
        newPasswordInputEditText = findViewById(R.id.newPasswordInputEditText)
        repeatPasswordInputEditText = findViewById(R.id.repeatPasswordInputEditText)
        acceptButton = findViewById(R.id.acceptButton)

        // Обработка нажатия на кнопку "Принять"
        acceptButton.setOnClickListener {
            val oldPassword = oldPasswordInputEditText.text.toString()
            val newPassword = newPasswordInputEditText.text.toString()
            val repeatPassword = repeatPasswordInputEditText.text.toString()

            if (validateInput(oldPassword, newPassword, repeatPassword)) {
                changePassword(oldPassword, newPassword)
            }
        }

        val toolbar = findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, ActivityPersonalArea::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun validateInput(oldPassword: String, newPassword: String, repeatPassword: String): Boolean {
        return when {
            oldPassword.isEmpty() -> {
                showToast("Введите старый пароль")
                false
            }
            newPassword.isEmpty() -> {
                showToast("Введите новый пароль")
                false
            }
            repeatPassword.isEmpty() -> {
                showToast("Повторите новый пароль")
                false
            }
            newPassword != repeatPassword -> {
                showToast("Пароли не совпадают")
                false
            }
            else -> true
        }
    }

    private fun changePassword(oldPassword: String, newPassword: String) {
        showToast("Пароль успешно изменен")
        returnToPersonalArea()
    }

    private fun returnToPersonalArea() {
        val intent = Intent(this, ActivityPersonalArea::class.java)
        startActivity(intent)
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}