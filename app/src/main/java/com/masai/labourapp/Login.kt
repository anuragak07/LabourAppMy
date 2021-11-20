package com.masai.labourapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var etEmail:EditText
    private lateinit var etPassword:EditText
    private lateinit var btnLogin: Button
    private lateinit var etNoAcc: TextView

    private lateinit var mAuth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        etEmail =findViewById(R.id.etEmail)
        etPassword=findViewById(R.id.etPassword)
        btnLogin=findViewById(R.id.btnSignIn)
        etNoAcc=findViewById(R.id.tvNoAccount)

        etNoAcc.setOnClickListener{
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            login(email,password)
        }

    }

    private fun login(email: String, password: String) {
        //logic part
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@Login,MainActivity::class.java)
                    finish()
                    startActivity(intent)


                } else {
                    Toast.makeText(this@Login,"Some Error Occured",Toast.LENGTH_SHORT).show()

                }

            }


    }
}