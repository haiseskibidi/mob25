package ru.fefu.heeello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val activityFragment = ActivityFragment()
    private val profileFragment = ProfileFragment()
    
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_activity -> {
                switchFragments(activityFragment, "activity")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                switchFragments(profileFragment, "profile")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.nav_host_fragment, activityFragment, "activity")
                .commit()
        }
    }

    private fun switchFragments(fragment: Fragment, tag: String) {
        val currentFragment = supportFragmentManager.fragments.find { it.isVisible }
        var newFragment = supportFragmentManager.findFragmentByTag(tag)

        if (currentFragment != null && currentFragment == newFragment) return

        val transaction = supportFragmentManager.beginTransaction()
        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }

        if (newFragment == null) {
            newFragment = if (tag == "activity") ActivityFragment() else ProfileFragment()
            transaction.add(R.id.nav_host_fragment, newFragment, tag)
        } else {
            transaction.show(newFragment)
        }
        transaction.commit()
    }
} 