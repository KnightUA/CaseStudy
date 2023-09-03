package ua.shtest.casestudy.di

import dagger.Component
import ua.shtest.casestudy.application.CaseStudyApplication
import ua.shtest.casestudy.di.network.NetworkModule
import ua.shtest.casestudy.di.viewmodel.ViewModelModule
import ua.shtest.casestudy.ui.activity.SingleActivity
import javax.inject.Singleton

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 02.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: CaseStudyApplication)
    fun inject(activity: SingleActivity)
}