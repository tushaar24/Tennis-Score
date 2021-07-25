package com.example.tennisscores.ui.fragments.atpRankingFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tennisscores.data.entities.Ranking
import com.example.tennisscores.databinding.ItemAtpRankPlayerBinding

class AtpRankingAdapter : RecyclerView.Adapter<AtpRankingAdapter.AtpRankingViewHolder>() {

    private var rankings : MutableList<Ranking> = mutableListOf()

    class AtpRankingViewHolder(private val binding: ItemAtpRankPlayerBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(ranking : Ranking){
            binding.tvPlayerName.text = ranking.fullName
            binding.tvRankings.text = ranking.ranking.toString()
            binding.tvPoints.text = ranking.rankingPoints.toString()
        }

        companion object{

            fun from(parent : ViewGroup) : AtpRankingViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemAtpRankPlayerBinding.inflate(layoutInflater, parent, false)
                return AtpRankingViewHolder(binding)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AtpRankingViewHolder {
        return AtpRankingViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: AtpRankingViewHolder, position: Int) {
        val currentPlayer = rankings[position]
        holder.bind(currentPlayer)
    }

    override fun getItemCount(): Int {
        return rankings.size
    }

    fun setData(ranking: MutableList<Ranking>){
        rankings.clear()

        for(rank in ranking){
            rankings.add(rank)
        }
        notifyDataSetChanged()
    }
}