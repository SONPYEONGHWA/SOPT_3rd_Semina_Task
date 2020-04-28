package com.example.sopt_2nd_semina_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_viewpager.adapter = MainPagerAdapter(supportFragmentManager)
        main_viewpager.offscreenPageLimit = 2

        main_bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_home -> main_viewpager.currentItem = 0
                R.id.menu_book -> main_viewpager.currentItem = 1
                R.id.menu_person -> main_viewpager.currentItem = 2
            }
            true
        }
    }
}
