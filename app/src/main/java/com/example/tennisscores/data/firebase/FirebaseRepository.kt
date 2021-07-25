package com.example.tennisscores.data.firebase

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirebaseRepository {

    private var mAuth = Firebase.auth
    private var userId : String? = null
    var googleSignInClient : GoogleSignInClient? = null

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
            false
        }
    }

    fun getCurrentUser(): FirebaseUser? {
        return mAuth.currentUser
    }

    fun logOutUser(){
        mAuth.signOut()
    }

    suspend fun createUserWithEmailAndPassword(
        email : String,
        password: String
    ): Boolean{
        return try{
             mAuth.createUserWithEmailAndPassword(
                email,
                password
            ).await()
            true
        }catch (e : java.lang.Exception){
            false
        }
    }

    fun googleSignOut(){
        googleSignInClient?.signOut()
    }

}