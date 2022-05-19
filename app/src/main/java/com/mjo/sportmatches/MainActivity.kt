package com.mjo.sportmatches

import android.net.Uri
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.util.Log

import android.view.MenuItem
import android.view.View
import java.time.LocalDateTime
import android.widget.CalendarView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.mjo.sportmatches.api.RetrofitInstance.api
import com.mjo.sportmatches.data.Matches
import com.mjo.sportmatches.Converter
import retrofit2.*
import java.time.Month
import java.time.Year
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    var calendarView:CalendarView?=null
    lateinit var rcView:RecyclerView
    private var sport:String = "soccer"
    lateinit var info:TextView
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rcView = findViewById(R.id.rcView)

        info = findViewById(R.id.info)

        var drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        var navView: NavigationView = findViewById(R.id.nav_view)
        navView.itemIconTintList = null;
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatted = current.format(formatter)
        val day = LocalDateTime.now().dayOfMonth.toString()
        val month = LocalDateTime.now().monthValue.toString()
        val year = LocalDateTime.now().year.toString()
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.basketballItem -> {
                    sport = "basketball"
                    getDataOfMatches(sport,formatted,day, month = month, year = year)
                }
                R.id.soccerItem -> {
                    sport = "soccer"
                    getDataOfMatches(sport,formatted,day, month = month, year = year)
                }
                R.id.hockeyItem -> {
                    sport = "hockey"
                    getDataOfMatches(sport,formatted,day, month = month, year = year)
                }
                R.id.tennisItem -> {
                    sport = "tennis"
                    getDataOfMatches(sport,formatted,day, month = month, year = year)

                }
                R.id.cricketItem -> {
                    sport = "cricket"
                    getDataOfMatches(sport,formatted,day, month = month, year = year)
                }
            }
            true
        }

        var headerLayout = navView.getHeaderView(0)
        calendarView= headerLayout.findViewById(R.id.calendarView)

        calendarView?.setOnDateChangeListener { view, year, month, dayOfMonth ->
            var mStr = ""
            var dStr = ""
            mStr = if (month + 1 <= 9) "0${month+1}"
                else (month + 1).toString()
            dStr = if (dayOfMonth <= 9) "0${dayOfMonth+1}"
                else (dayOfMonth).toString()
            Toast.makeText(
                applicationContext,
                "You set $dStr/$mStr/$year",
                Toast.LENGTH_SHORT
            ).show()
            var date = "$year$mStr$dStr"
            getDataOfMatches(sport,date,dStr,mStr,year.toString())
            }
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }


    fun openPolitics(view: View){
        val url = "https://www.google.com/"
        val customTabsIntent: CustomTabsIntent = CustomTabsIntent.Builder().setColorScheme(
            CustomTabsIntent.COLOR_SCHEME_DARK).build()
        customTabsIntent.launchUrl(this, Uri.parse(url));
    }

    private fun getDataOfMatches(sport:String, date:String ,day:String ,month:String ,year: String){
        info.visibility = View.GONE
        api.getMatchesList(sport,date).enqueue(object: Callback<Matches>{

            override fun onFailure(call: Call<Matches>, t: Throwable) {
                Log.e("ERROR","$t")
            }

            override fun onResponse(
                call: Call<Matches>,
                response: Response<Matches>
            ) {
                if (!response.isSuccessful){
                    Toast.makeText(applicationContext,"Oops! Something went wrong",Toast.LENGTH_SHORT).show()
                }
                var convert:Converter = Converter(response)
                var gameList = convert.convertToList()
                rcView.layoutManager = LinearLayoutManager(this@MainActivity)
                rcView.adapter = MatchAdapter(gameList,day,month,year)
                //              Log.d("match", )
            }

        })
    }

}
