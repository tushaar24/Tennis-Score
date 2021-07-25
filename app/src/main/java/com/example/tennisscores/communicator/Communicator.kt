package com.example.tennisscores.communicator

import com.example.tennisscores.data.firebase.FirebaseRepository
import com.example.tennisscores.data.retrofit.RemoteServicesApi
import com.example.tennisscores.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

        fun getRemoteApiServices() : RemoteServicesApi{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RemoteServicesApi::class.java)
        }
    }
}