package ua.shtest.casestudy.di

import dagger.Component
import ua.shtest.casestudy.application.CaseStudyApplication
import javax.inject.Singleton

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 02.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(application: CaseStudyApplication)
}