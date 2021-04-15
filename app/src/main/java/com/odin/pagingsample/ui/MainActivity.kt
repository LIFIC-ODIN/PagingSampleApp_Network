package com.odin.pagingsample.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.odin.pagingsample.R
import com.odin.pagingsample.databinding.ActivityMainBinding
import com.odin.pagingsample.util.PREF_DARK_THEME
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var preferences: SharedPreferences
    private var darkModeEnabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        setupData()
    }

    private fun setupData() {
        binding.run {
            viewModel = mainViewModel
        }

        mainViewModel.clearDatabase()

        binding.tvTheme.setOnClickListener {
            darkModeEnabled = preferences.getBoolean(PREF_DARK_THEME, false)
            darkModeEnabled = !darkModeEnabled
            preferences.edit {
                putBoolean(PREF_DARK_THEME, darkModeEnabled)
            }
            if (darkModeEnabled) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
            }
            recreate()
        }
    }
}