package me.justmambo.play.calorie

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Sun 26 Feb 2023
 */
@HiltAndroidApp
class CalorieApp : Application() {
    override fun onCreate() {
        super.onCreate()
        plantTimber()
    }

    private fun plantTimber(){
        Timber.plant(Timber.DebugTree())
    }
}