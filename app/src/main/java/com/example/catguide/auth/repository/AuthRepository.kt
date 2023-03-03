package com.example.catguide.auth.repository

import com.example.catguide.constants.UiState
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class AuthRepository () {
    private val firebaseAuth= Firebase.auth
    //open
    fun signIn(email:String, password:String): Task<AuthResult>{
       return firebaseAuth.signInWithEmailAndPassword(email,password)
    }
    //kayıt
    fun signUp(email: String,password: String):Task<AuthResult>{
       return firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
        }

    }
    //exit
    fun signOut(){
        firebaseAuth.signOut()

    }
    //mevcut kullnaıcıyı alma
    fun getcurrentUser():FirebaseUser?{
        return firebaseAuth.currentUser
    }
}