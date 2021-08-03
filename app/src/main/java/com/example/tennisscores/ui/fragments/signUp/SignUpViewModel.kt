package com.example.tennisscores.ui.fragments.signUp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tennisscores.communicator.Communicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private var _createUserSuccess = MutableLiveData<Boolean>()
    val createUser get() = _createUserSuccess

    fun createUser(
        email : String,
        password : String
    ){
        viewModelScope.launch(Dispatchers.IO) {
            _createUserSuccess.postValue(
                Communicator.getFirebaseRepository()
                    .createUserWithEmailAndPassword(
                        email,
                        password
                    )
            )

            Log.d("signUp", createUser.value.toString())
        }
    }
}