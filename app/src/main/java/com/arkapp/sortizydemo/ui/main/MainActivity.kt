package com.arkapp.sortizydemo.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.arkapp.sortizydemo.R
import com.arkapp.sortizydemo.data.repository.PrefRepository
import com.arkapp.sortizydemo.utils.hide
import com.arkapp.sortizydemo.utils.show
import com.arkapp.sortizydemo.utils.showAlertDialog
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val prefRepository by lazy { PrefRepository(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.fragment)

        val appBarConfiguration = AppBarConfiguration
            .Builder(
                R.id.homeFragment,
                R.id.upcomingApptFragment,
            )
            .build()

        setupWithNavController(bottomNavigation, navController)
        setupActionBarWithNavController(this, navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.splashFragment || destination.id == R.id.signUpFragment) {
                supportActionBar?.hide()
                bottomNavigation.hide()
            } else {
                supportActionBar?.show()
                bottomNavigation.show()
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.logout_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
            R.id.logout -> {
                showAlertDialog(
                    getString(R.string.logout),
                    getString(R.string.logout_desc),
                    getString(R.string.logout),
                    getString(R.string.cancel),
                    ) { dialog, _ ->
                    prefRepository.setLoggedIn(false)
                    findNavController(R.id.fragment).navigate(R.id.action_homeFragment_to_splashFragment)
                    dialog.dismiss()
                }
            }
        }
        return true
    }

}
