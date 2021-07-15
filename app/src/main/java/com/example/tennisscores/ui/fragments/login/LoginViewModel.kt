package com.example.tennisscores.ui.fragments.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tennisscores.communicator.Communicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private var _signInSuccess : MutableLiveData<Boolean> = MutableLiveData()
    val signInSuccess get() = _signInSuccess

    fun signInUser(
        emailId : String,
        password : String
    ){
        viewModelScope.launch(Dispatchers.IO) {
            _signInSuccess.postValue(Communicator
                .getFirebaseRepository()
                .signInUser(emailId, password))
        }
    }
}