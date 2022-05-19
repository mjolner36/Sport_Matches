package com.mjo.sportmatches

import com.mjo.sportmatches.data.Game
import com.mjo.sportmatches.data.Matches
import retrofit2.Response

class Converter(val match:Response<Matches>) {
    private var body = match.body()

    fun convertToList(): MutableList<Game> {
        val stageSize = getSizeOfStages()
        val listOfGames = mutableListOf<Game>()
        val arrayOfEvents = getSizeOfEvents(stageSize)
        val sumMatchesPerDay = totalMatchesPerDay(arrayOfEvents)
        var countMatches = 0
        for (stage in 0 until stageSize){
            for (gamesPerEvent in 0 until arrayOfEvents[stage]){
               var team1:String = body?.Stages?.get(stage)?.Events?.get(gamesPerEvent)?.T1?.get(0)!!.Nm
               var team2:String = body?.Stages?.get(stage)?.Events?.get(gamesPerEvent)?.T2?.get(0)!!.Nm
                var imageTeam1 =  body?.Stages?.get(stage)?.Events?.get(gamesPerEvent)?.T1?.get(0)!!.Img
                var imageTeam2 =  body?.Stages?.get(stage)?.Events?.get(gamesPerEvent)?.T2?.get(0)!!.Img
                var score1 =  body?.Stages?.get(stage)?.Events?.get(gamesPerEvent)?.Tr1
                var score2 =  body?.Stages?.get(stage)?.Events?.get(gamesPerEvent)?.Tr2
                var game:Game = Game(team1,team2,imageTeam1,imageTeam2,score1,score2)
                listOfGames.add(game)
                    countMatches++
            }
        }
        return listOfGames
    }

    private fun getSizeOfStages():Int = body?.Stages!!.size

    private fun getSizeOfEvents(stages:Int):ArrayList<Int> {

        var arrEvents: ArrayList<Int> = ArrayList(stages)
        for (index in 0 until stages){
            arrEvents.add(body?.Stages?.get(index)?.Events!!.size)
        }
        return arrEvents
    }

    private fun totalMatchesPerDay(arr:ArrayList<Int>):Int{
        var sum = 0
        for (i in arr){
            sum+=i
        }
        return sum
    }



}