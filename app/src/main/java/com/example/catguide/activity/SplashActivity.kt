package com.example.catguide.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import com.example.catguide.MainActivity
import com.example.catguide.R
import com.example.catguide.auth.viewmodel.AuthViewmodel
import com.example.catguide.databinding.ActivitySplashBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    val splashScreen=4700
    private val authViewmodel:AuthViewmodel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val anim_one = AnimationUtils.loadAnimation(this, R.anim.left_anim)
        val anim_two = AnimationUtils.loadAnimation(this, R.anim.right_anim)
        val anim_three = AnimationUtils.loadAnimation(this, R.anim.down_anim)
        val anim_four = AnimationUtils.loadAnimation(this, R.anim.txt_anim)

        binding.apply {
            imageViewLeft.animation=anim_one
            imageViewRight.animation=anim_two
            imgDownCat.animation=anim_three
            txtCatGuide.animation=anim_four


        }
        val mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser
        //splah screnn
        Handler().postDelayed({

                if (currentUser !=null){
                    // Kullanıcı zaten oturum açmış, Ana Sayfaya git
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    val intent= Intent(this@SplashActivity, RegisterAcivity::class.java)
                    startActivity(intent)
                    finish()
                }


        },splashScreen.toLong())

    }
}