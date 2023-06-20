package com.example.signinsignup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.signinsignup.SignIn.Companion.KEY1
import com.google.android.material.textfield.TextInputEditText

class WelcomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)

        val mail = intent.getStringExtra(SignIn.KEY1)
        val name = intent.getStringExtra(SignIn.KEY2)
        val id = intent.getStringExtra(SignIn.KEY3)

        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val tvName = findViewById<TextView>(R.id.tvName)
        val tvUserName = findViewById<TextView>(R.id.tvUserName)

        tvWelcome.text="Welcome $name"
        tvName.text="Mail :  $mail"
        tvUserName.text="UserName : $id"
    }
}