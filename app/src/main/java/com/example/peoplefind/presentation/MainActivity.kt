package com.example.peoplefind.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.peoplefind.R
import com.example.peoplefind.databinding.ActivityMainBinding
import com.example.peoplefind.presentation.fragment.HomeFragment

class MainActivity : AppCompatActivity() {
    private val fragmentManager by lazy { supportFragmentManager }
    private lateinit var activeFragment: Fragment
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initFragments()
        setContentView(R.layout.activity_main)
    }

    private fun initFragments() = with(binding) {
        fragmentManager.beginTransaction().apply {
            add(R.id.containerFragment, HomeFragment.newInstance())
        }.commit()

        activeFragment = fragmentManager.fragments.first()

        navMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menuHome -> {
                    val homeFragment = fragmentManager.fragments[0]
                    fragmentManager.beginTransaction()
                        .hide(activeFragment)
                        .show(homeFragment)
                        .commit()
                    activeFragment = homeFragment
                }
                R.id.menuChat -> {}
                R.id.menuProfile -> {}
            }
            true
        }
    }
}