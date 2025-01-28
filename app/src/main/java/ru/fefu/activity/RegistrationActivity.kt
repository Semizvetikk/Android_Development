package ru.fefu.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class RegistrationActivity : AppCompatActivity(R.layout.activity_registration) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, Activity::class.java)
            startActivity(intent)
            finish()
        }

        // Инициализация элементов ввода
        val loginInputEditText = findViewById<TextInputEditText>(R.id.loginInputEditText)
        val passwordInputEditText = findViewById<TextInputEditText>(R.id.passwordInputEditText)
        val confirmPasswordInputEditText = findViewById<TextInputEditText>(R.id.confirmPasswordInputEditText)
        val nameInputEditText = findViewById<TextInputEditText>(R.id.nameInputEditText)
        val agreementCheckBox = findViewById<CheckBox>(R.id.agreementCheckBox)
        val genderRadioGroup = findViewById<RadioGroup>(R.id.genderRadioGroup)
        val registerButton = findViewById<MaterialButton>(R.id.registerButton)

        val agreementText = getString(R.string.agreement)
        val spannableString = SpannableString(agreementText)

        val privacyPolicyStart = agreementText.indexOf("политикой конфиденциальности")
        val privacyPolicyEnd = privacyPolicyStart + "политикой конфиденциальности".length

        val termsOfServiceStart = agreementText.indexOf("пользовательское соглашение")
        val termsOfServiceEnd = termsOfServiceStart + "пользовательское соглашение".length

        spannableString.setSpan(
            object : ClickableSpan() {
                override fun onClick(widget: View) {

                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://a.d-cd.net/5bf833cs-960.jpg"))
                    startActivity(intent)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.color = ContextCompat.getColor(this@RegistrationActivity, R.color.purple_500)
                    ds.isUnderlineText = false
                }
            },
            privacyPolicyStart,
            privacyPolicyEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableString.setSpan(
            object : ClickableSpan() {
                override fun onClick(widget: View) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://frankfurt.apollo.olxcdn.com/v1/files/jo7zb24ido25-KZ/image;s=600x0;q=50"))
                    startActivity(intent)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.color = ContextCompat.getColor(this@RegistrationActivity, R.color.purple_500)
                    ds.isUnderlineText = false
                }
            },
            termsOfServiceStart,
            termsOfServiceEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        agreementCheckBox.text = spannableString
        agreementCheckBox.movementMethod = LinkMovementMethod.getInstance()


        registerButton.setOnClickListener {
            val login = loginInputEditText.text.toString()
            val password = passwordInputEditText.text.toString()
            val confirmPassword = confirmPasswordInputEditText.text.toString()
            val name = nameInputEditText.text.toString()

            val selectedGender = when (genderRadioGroup.checkedRadioButtonId) {
                R.id.maleRadioButton -> "Мужской"
                R.id.femaleRadioButton -> "Женский"
                else -> null
            }

            // Проверка заполнения полей
            if (login.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || name.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
            } else if (!agreementCheckBox.isChecked) {
                Toast.makeText(this, "Примите соглашение", Toast.LENGTH_SHORT).show()
            } else if (selectedGender == null) {
                Toast.makeText(this, "Выберите пол", Toast.LENGTH_SHORT).show()
            } else {
                // Регистрация прошла успешно
                Toast.makeText(this, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ActivityEmptyState::class.java)
                startActivity(intent)
                finish()
            }

        }
    }
}