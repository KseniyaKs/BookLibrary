package com.example.myapplicationone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager.widget.PagerTabStrip
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplicationone.Fragment.LikeFragment
import com.example.myapplicationone.Fragment.MainFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.mainContainer, MainFragment.newInstance())
                .commit()
        }

        val bottomBar = findViewById<BottomNavigationView>(R.id.bottom_navigation_bar)
        bottomBar.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.mainFragment -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.mainContainer,MainFragment.newInstance())
                        .commit()
                    return@setOnItemSelectedListener true
                }
                R.id.likeFragment -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.mainContainer, LikeFragment.newInstance())
                        .commit()
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener false
            }
        }

//        val navHostFragment = supportFragmentManager
//            .findFragmentById(R.id.mainContainer) as NavHostFragment
//        val navController = navHostFragment.navController
//        val appBarConfiguration = AppBarConfiguration.Builder(setOf(R.id.mainContainer))

//        setupActionBarWithNavController(navController)

//        bottomBar.setupWithNavController(navController)

//        <androidx.fragment.app.FragmentContainerView
//        android:id="@+id/fragmentContainerView"
//        android:name="androidx.navigation.fragment.NavHostFragment"
//        android:layout_width="0dp"
//        android:layout_height="0dp"
//        app:defaultNavHost="true"
//        app:layout_constraintBottom_toTopOf="@+id/bottom_navigation_bar"
//        app:layout_constraintEnd_toEndOf="parent"
//        app:layout_constraintStart_toStartOf="parent"
//        app:layout_constraintTop_toTopOf="parent"
//        app:navGraph="@navigation/my_nav" />

    }
}