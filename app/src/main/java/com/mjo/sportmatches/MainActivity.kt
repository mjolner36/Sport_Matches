package com.mjo.sportmatches

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var calendarView: CalendarView
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



        var headerLayout: View = layoutInflater.inflate(R.layout.nav_header, null)
        calendarView= headerLayout.findViewById(R.id.calendarView)
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
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
