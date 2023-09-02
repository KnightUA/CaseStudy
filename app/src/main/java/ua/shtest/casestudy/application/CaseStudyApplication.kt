package ua.shtest.casestudy.application

import android.app.Application
import timber.log.Timber
import ua.shtest.casestudy.BuildConfig
import ua.shtest.casestudy.di.ApplicationComponent
import ua.shtest.casestudy.di.DaggerApplicationComponent

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 02.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

class CaseStudyApplication : Application() {

    val appComponent: ApplicationComponent by lazy { DaggerApplicationComponent.create() }

    override fun onCreate() {
        super.onCreate()

        injectMembers()
        initializeTimber()
    }

    private fun injectMembers() = appComponent.inject(this)

    private fun initializeTimber() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}