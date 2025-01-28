package ru.fefu.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class ActivityEmptyState : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty_state)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val titleText = findViewById<TextView>(R.id.titleText)
        val startTrackingText = findViewById<TextView>(R.id.startTrackingText)

        // Настраиваем адаптер для ViewPager2
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 1) {
                    titleText.visibility = View.GONE
                    startTrackingText.visibility = View.GONE
                } else {
                    titleText.visibility = View.VISIBLE
                    startTrackingText.visibility = View.VISIBLE
                }
            }
        })

        // Связываем TabLayout с ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Моя"
                1 -> "Пользователей"
                else -> null
            }
        }.attach()

        // Находим BottomNavigationView и настраиваем обработчик выбора
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.activityTab -> {
                    true
                }
                R.id.profileTab -> {
                    val intent = Intent(this, ActivityPersonalArea::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        // Находим FloatingActionButton и настраиваем обработчик нажатия
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, NewActivity::class.java)
            startActivity(intent)
            finish()
        }

        // По умолчанию открываем экран активности
        if (savedInstanceState == null) {
            replaceFragment(ActivityFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
