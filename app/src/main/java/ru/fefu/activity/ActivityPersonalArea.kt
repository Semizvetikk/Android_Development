package ru.fefu.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class ActivityPersonalArea : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_area)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.profileTab
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.activityTab -> {
                    val intent = Intent(this, ActivityEmptyState::class.java)
                    startActivity(intent)
                    true
                }

                R.id.profileTab -> {
                    true
                }

                else -> false
            }
        }

        val loginInputEditText = findViewById<TextInputEditText>(R.id.loginInputEditText)
        val passwordInputEditText = findViewById<TextInputEditText>(R.id.passwordInputEditText)

        loginInputEditText.setText("Vetochka")
        passwordInputEditText.setText("semizvetikk")

        val saveButton = findViewById<MaterialButton>(R.id.saveButton)

        saveButton.setOnClickListener {
            // Получаем новые значения из полей
            val newLogin = loginInputEditText.text.toString()
            val newNickname = passwordInputEditText.text.toString()

            showToast("Данные сохранены!")

            val intent = Intent(this, ActivityEmptyState::class.java)
            startActivity(intent)
            finish()
        }

        // Инициализация кнопки "Изменить пароль"
        val changePasswordButton = findViewById<MaterialButton>(R.id.changePasswordButton)
        changePasswordButton.setOnClickListener {
            val intent = Intent(this, ChangePasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}