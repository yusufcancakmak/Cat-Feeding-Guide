package com.example.catguide.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.catguide.MainActivity
import com.example.catguide.auth.viewmodel.AuthViewmodel
import com.example.catguide.databinding.ActivitySignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private  val  authViewmodel:AuthViewmodel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSignUpSign.setOnClickListener {
            val email=binding.etEmail.text.toString()
            val password=binding.etPassword.text.toString()
            authViewmodel.createUserWithEmailAndPassword(email, password)
            val intent = Intent(this, RegisterAcivity::class.java)
            startActivity(intent)
        }
        binding.btnSignUpLogin.setOnClickListener {
            val email=binding.etEmail.text.toString()
            val password=binding.etPassword.text.toString()
            authViewmodel.signInWithEmailAndPassword(email, password)


        }
        authViewmodel.userLiveData.observe(this, { firebaseUser ->
            if (firebaseUser != null) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                // Kullanıcı giriş yaptı veya kayıt oldu
                // Uygulama ana ekranına geçiş yapabilirsiniz
                Toast.makeText(this, "girdi!", Toast.LENGTH_SHORT).show()
            } else {
                // Kullanıcı giriş yapamadı veya kayıt olamadı
                Toast.makeText(this, "E-posta veya şifre hatalı!", Toast.LENGTH_SHORT).show()
            }
        })



    }
}