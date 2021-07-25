package com.example.tennisscores.ui.fragments.atpRankingFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tennisscores.communicator.Communicator
import com.example.tennisscores.data.entities.Ranking
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AtpRankingViewModel : ViewModel() {
    val rankings : MutableLiveData<MutableList<Ranking>> = MutableLiveData()

    fun getAtpSinglesMenRanking(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = Communicator.getRemoteApiServices().getAtpMenSinglesRanking()
            rankings.postValue(response.body()?.results?.rankings)
        }
    }
}