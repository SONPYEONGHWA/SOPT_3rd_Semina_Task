package com.example.sopt_2nd_semina_task.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.sopt_2nd_semina_task.fragment.HomeFragment
import com.example.sopt_2nd_semina_task.fragment.LibraryFragment
import com.example.sopt_2nd_semina_task.fragment.MyPageFragment

class MainPagerAdapter (fm:FragmentManager) :FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> HomeFragment()
            1 -> LibraryFragment()
            else -> MyPageFragment()
        }
    }

    override fun getCount() = 3
}