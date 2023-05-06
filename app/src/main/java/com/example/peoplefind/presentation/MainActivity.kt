package com.example.peoplefind.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.peoplefind.R
import com.example.peoplefind.databinding.ActivityMainBinding
import com.example.peoplefind.presentation.fragment.HomeFragment
import com.example.peoplefind.presentation.fragment.MessengerFragment

class MainActivity : AppCompatActivity() {
    private val fragmentManager by lazy { supportFragmentManager }
    private val homeFragment by lazy { HomeFragment.newInstance() }
    private val messengerFragment by lazy { MessengerFragment.newInstance() }
    private lateinit var activeFragment: Fragment
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initFragments()
        setContentView(binding.root)
    }

    private fun initFragments() = with(binding) {
        activeFragment = homeFragment

        navMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menuHome -> {
                    openFragment(homeFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.menuChat -> {
                    openFragment(messengerFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.menuProfile -> {
                    return@setOnItemSelectedListener false
                }
                else -> return@setOnItemSelectedListener false
            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        fragmentManager.beginTransaction()
            .replace(R.id.containerFragment, fragment)
            .commit()
        activeFragment = fragment
    }
}