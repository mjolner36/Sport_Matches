package com.mjo.sportmatches

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mjo.sportmatches.data.Game
import com.mjo.sportmatches.databinding.ItemMatchCardBinding
import com.squareup.picasso.Picasso

class MatchAdapter(
    private val matchesList: MutableList<Game>,
    private val day:String,
    private val month:String,
    private val year:String
): RecyclerView.Adapter<MatchAdapter.MatchHolder>() {

    class MatchHolder(item: View,var day: String,var month: String,var year: String):RecyclerView.ViewHolder(item) {
        private val binding = ItemMatchCardBinding.bind(item)
        fun bind(game: Game) =with(binding) {
            team1TextView.text = game.nameTeam1
            team2TextView.text = game.nameTeam2
            dateTextView.text = "$day/$month/$year"
            score1.text = game.score1
            score2.text = game.score2

            Picasso.get()
                .load("https://lsm-static-prod.livescore.com/medium/"+game.imageTeam1)
                .into(team1ImageView)
            Picasso.get()
                .load("https://lsm-static-prod.livescore.com/medium/"+game.imageTeam2)
                .into(team2ImageView)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match_card,parent,false)
        return MatchHolder(view,day, month, year)
    }

    override fun onBindViewHolder(holder: MatchHolder, position: Int) {
        holder.bind(matchesList[position])
    }

    override fun getItemCount(): Int {
        return matchesList.size
    }
}