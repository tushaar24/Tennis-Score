package com.example.tennisscores.ui.fragments.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tennisscores.communicator.Communicator
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private var _signInWithEmailAndPasswordSuccess : MutableLiveData<Boolean> = MutableLiveData()
    val signInWithEmailAndPasswordSuccess get() = _signInWithEmailAndPasswordSuccess
    lateinit var googleSignInClient : GoogleSignInClient

    private var _signInWithGoogle : MutableLiveData<Boolean> = MutableLiveData()
    val signInWithGoogle get() = _signInWithGoogle

    private val currentUser = Communicator.getFirebaseRepository().getCurrentUser()

    fun isUserSignedIn() : Boolean{
        return currentUser != null
    }

    fun signInUser(
        emailId : String,
        password : String
    ){
        viewModelScope.launch(Dispatchers.IO) {
            _signInWithEmailAndPasswordSuccess.postValue(Communicator
                .getFirebaseRepository()
                .signInUserWithEmailAndPassword(emailId, password))
        }
    }

    fun firebaseAuthWithGoogle(idToken : String){
        _signInWithGoogle.postValue(Communicator.getFirebaseRepository().firebaseAuthWithGoogle(idToken))
    }

    fun setGoogleSignInClient(context: Context, gso : GoogleSignInOptions){
        Communicator.getFirebaseRepository().googleSignInClient = GoogleSignIn.getClient(context, gso)
        Log.d("google", "done")
        googleSignInClient = Communicator.getFirebaseRepository().googleSignInClient!!
    }

}