package com.masai.labourapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {
    private lateinit var etName:EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnSignUp: Button
    private lateinit var tvAlreadyRegis: TextView
    private lateinit var mAuth:FirebaseAuth
    private lateinit var mDbRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth = FirebaseAuth.getInstance()
        etName = findViewById(R.id.etNameSignUp)
        etEmail =findViewById(R.id.EmailSignup)
        etPassword=findViewById(R.id.etPasswordSignUp)
        etConfirmPassword=findViewById(R.id.etConfirmPassword)
        btnSignUp=findViewById(R.id.btnSignUp)
        tvAlreadyRegis=findViewById(R.id.alreadySignIn)

        btnSignUp.setOnClickListener {
            val email = etEmail.text.toString()
            val name = etName.text.toString()
           signUp(name,email)

        }
    }

    private fun signUp(name:String,email:String) {

        val password=etPassword.text.toString()
        val confirmPassword =etConfirmPassword.text.toString()
        val name = etName.text.toString()

        //logic part
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //code for jumping to home activity
                        addUserToDatabase(name,email,mAuth.currentUser?.uid!!)
                    Toast.makeText(this@SignUp, "Registered Successfully", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(this@SignUp,MainActivity::class.java)
                    finish()
                    startActivity(intent)


                } else {
                    Toast.makeText(this@SignUp, "SignUp Error", Toast.LENGTH_SHORT)
                        .show()

                }
            }

    }

    private fun addUserToDatabase(name: String, email: String, uid: String) {
        mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(User(name,email,uid))




    }
}