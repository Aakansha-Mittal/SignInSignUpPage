package com.example.signinsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSignUp = findViewById<Button>(R.id.btnSignUp)
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etGmail = findViewById<TextInputEditText>(R.id.etGmail)
        val etId = findViewById<TextInputEditText>(R.id.etId)
        val etPsswd = findViewById<TextInputEditText>(R.id.etPsswd)
        val tvSignIn = findViewById<TextView>(R.id.tvSignIn)

        btnSignUp.setOnClickListener{
            val name = etName.text.toString()
            val mail = etGmail.text.toString()
            val uniqueId = etId.text.toString()
            val password = etPsswd.text.toString()

            val user = User(name, mail, password, uniqueId)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(uniqueId).setValue(user).addOnSuccessListener{
                etName.text?.clear()
                etGmail.text?.clear()
                etId.text?.clear()
                etPsswd.text?.clear()

                Toast.makeText(this, "User data entered successfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

        }

        tvSignIn.setOnClickListener{
            val SignInActivity = Intent(this, SignIn::class.java)
            startActivity(SignInActivity)
        }
    }
}