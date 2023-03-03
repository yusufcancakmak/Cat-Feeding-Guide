package com.example.catguide.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.catguide.MainActivity
import com.example.catguide.auth.viewmodel.AuthViewmodel
import com.example.catguide.databinding.ActivityRegisterAcivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterAcivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterAcivityBinding
    private val authViewmodel:AuthViewmodel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterAcivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener {
            val intent= Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            val email=binding.etEmail.text.toString()
            val password=binding.etPassword.text.toString()
            authViewmodel.signInWithEmailAndPassword(email,password)
        }

        authViewmodel.userLiveData.observe(this, Observer { firebaseUser->
            if (firebaseUser !=null){
                val intent=Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                Toast.makeText(this,"Giriş yapıldı",Toast.LENGTH_SHORT).show()
            }
            else {
                // Kullanıcı giriş yapamadı veya kayıt olamadı
                Toast.makeText(this, "E-posta veya şifre hatalı!", Toast.LENGTH_SHORT).show()

            }
        })

    }
}