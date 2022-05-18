package com.mjo.sportmatches

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.mjo.sportmatches.api.RetrofitInstance
import com.mjo.sportmatches.api.RetrofitInstance.retrofit
import com.mjo.sportmatches.data.StageListResponse
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    var calendarView:CalendarView?=null
    lateinit var sport:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        var drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        var navView: NavigationView = findViewById(R.id.nav_view)
        navView.itemIconTintList = null;
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.basketballItem -> {
                    sport = "basketball"
                }
                R.id.soccerItem -> {
                    sport = "soccer"
                }
                R.id.hockeyItem -> {
                    sport = "hockey"
                }
                R.id.tennisItem -> {
                    sport = "tennis"
                }
                R.id.cricketItem -> {
                    sport = "cricket"
                }
            }
            true
        }

        var headerLayout = navView.getHeaderView(0)
        calendarView= headerLayout.findViewById(R.id.calendarView)

        calendarView?.setOnDateChangeListener { view, year, month, dayOfMonth ->
            var mStr = ""
            var dStr = ""
            mStr = if (month + 1 <= 9) "0$month"
                else (month + 1).toString()
            dStr = if (dayOfMonth <= 9) "0$month"
                else (dayOfMonth).toString()
            var data = "$year$mStr$dStr"
            Toast.makeText(
                applicationContext,
                "You set $dStr/$mStr/$year",
                Toast.LENGTH_SHORT
            ).show()

            }

        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }


}
