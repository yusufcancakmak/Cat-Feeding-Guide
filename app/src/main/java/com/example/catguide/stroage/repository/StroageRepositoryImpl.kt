package com.example.catguide.stroage.repository

import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface StroageRepository{
    suspend fun dowloadFile(path:String):ByteArray
}
class StroageRepositoryImpl @Inject constructor(private val firebaseStorage: FirebaseStorage):StroageRepository {
    override suspend fun dowloadFile(path: String): ByteArray {
        val photoRef = firebaseStorage.reference.child(path)

        return withContext(Dispatchers.IO){
            val bytes =photoRef.getBytes(Long.MAX_VALUE).await()
            bytes
        }
    }

}