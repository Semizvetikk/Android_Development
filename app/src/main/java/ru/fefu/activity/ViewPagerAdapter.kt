package ru.fefu.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MyFragment()   // Вкладка "Моя"
            1 -> UsersFragment() // Вкладка "Пользователей"
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}