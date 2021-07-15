package com.example.tennisscores.data.firebase

import android.util.Log
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirebaseRepository {

    private var mAuth = Firebase.auth
    private var userId : String? = null


    suspend fun signInUserWithEmailAndPassword(
        emailId : String,
        password : String
    ) : Boolean{
        return try {
           val result = mAuth.signInWithEmailAndPassword(
               emailId,
               password
           ).await()

            userId = result.user?.uid

            Log.d("user", userId.toString())

            true

       }catch (e : Exception){
           false
       }
    }

    fun firebaseAuthWithGoogle(idToken: String) : Boolean{
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        return try {
            mAuth.signInWithCredential(credential)
            true
        }catch (e : Exception){
            Log.d("Error", e.toString())
            false
        }
    }


}