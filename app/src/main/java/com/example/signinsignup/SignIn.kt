package com.example.signinsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {

    lateinit var database : DatabaseReference
    companion object{
        const val KEY1 = "com.example.signinsignup.SignIn.mail"
        const val KEY2 = "com.example.signinsignup.SignIn.name"
        const val KEY3 = "com.example.signinsignup.SignIn.id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val etId = findViewById<TextInputEditText>(R.id.etId)
        val btnSignIn = findViewById<Button>(R.id.btnSignIn)

        btnSignIn.setOnClickListener{
            val uniqueId = etId.text.toString()
            if(uniqueId.isNotEmpty()){
                readData(uniqueId)
            }else {
                Toast.makeText(this, "Please enter user Id", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun readData(uniqueId : String){
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(uniqueId).get().addOnSuccessListener{
            if(it.exists()){
                val mail = it.child("mail").value
                val name = it.child("name").value
                val psswd = it.child("psswd").value
                val userId = it.child("uniqueId").value
                val intent = Intent(this, WelcomePage::class.java)
                intent.putExtra(KEY1, mail.toString())
                intent.putExtra(KEY2, name.toString())
                intent.putExtra(KEY3, userId.toString())
                startActivity(intent)

            }else {
                Toast.makeText(this, "You're a new user. Please Sign Up", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }

}