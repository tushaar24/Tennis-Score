package com.example.tennisscores.communicator

import com.example.tennisscores.data.firebase.FirebaseRepository

class Communicator {

    companion object{

        @Volatile
        private var firebaseRepository : FirebaseRepository? = null

        fun getFirebaseRepository() : FirebaseRepository{
            if(firebaseRepository == null){
                synchronized(Communicator::class.java){
                    if(firebaseRepository == null){
                        firebaseRepository = FirebaseRepository()
                    }
                }
            }
            return firebaseRepository!!
        }
    }
}