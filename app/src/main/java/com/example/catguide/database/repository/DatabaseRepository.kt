package com.example.catguide.database.repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.catguide.database.model.Veri
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val firebaseDatabase: FirebaseDatabase) {
    private val myRef =Firebase.database.getReference("veriler")

    fun getVeriler():LiveData<List<Veri>> {
        val data=MutableLiveData<List<Veri>>()

        myRef.addValueEventListener(object  : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<Veri>()
                for (verisnapshot in snapshot.children){
                    val veri = verisnapshot.getValue(Veri::class.java)
                    veri!!.let {
                        list.add(it)
                    }
                }
                data.value=list
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Failed to read value",error.message.toString())

            }
        })
        return data
    }
}