package com.example.tennisscores.ui.fragments.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tennisscores.communicator.Communicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private var _signInWithEmailAndPasswordSuccess : MutableLiveData<Boolean> = MutableLiveData()
    val signInWithEmailAndPasswordSuccess get() = _signInWithEmailAndPasswordSuccess

    private var _signInWithGoogle : MutableLiveData<Boolean> = MutableLiveData()
    val signInWithGoogle get() = _signInWithGoogle

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
}