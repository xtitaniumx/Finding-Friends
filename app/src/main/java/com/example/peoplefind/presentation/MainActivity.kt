package com.example.peoplefind.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.peoplefind.R
import com.example.peoplefind.databinding.ActivityMainBinding
import com.example.peoplefind.presentation.fragment.HomeFragment
import com.example.peoplefind.presentation.fragment.MessengerFragment
import com.example.peoplefind.presentation.fragment.ProfileFragment

class MainActivity : AppCompatActivity() {
    private val fragmentManager by lazy { supportFragmentManager }
    private lateinit var activeFragment: Fragment
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initFragments()
        setContentView(binding.root)
    }

    private fun initFragments() = with(binding) {
        activeFragment = HomeFragment.newInstance()

        navMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menuHome -> {
                    openFragment(HomeFragment.newInstance())
                    return@setOnItemSelectedListener true
                }
                R.id.menuChat -> {
                    openFragment(MessengerFragment.newInstance())
                    return@setOnItemSelectedListener true
                }
                R.id.menuProfile -> {
                    openFragment(ProfileFragment.getInstance())
                    return@setOnItemSelectedListener true
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