package com.example.tennisscores.data.firebase

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class FirebaseRepository {

    private var mAuth = Firebase.auth

    suspend fun signInUser(
        emailId : String,
        password : String
    ) : Boolean{
        return try {
           mAuth.signInWithEmailAndPassword(
               emailId,
               password
           ).await()
           true
       }catch (e : Exception){
           false
       }
    }

}