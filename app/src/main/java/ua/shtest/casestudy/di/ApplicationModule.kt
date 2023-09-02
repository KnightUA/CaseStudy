package ua.shtest.casestudy.di

import dagger.Binds
import dagger.Module
import ua.shtest.casestudy.domain.repository.ItemsRxRepository
import ua.shtest.casestudy.domain.repository.NetworkItemsRxRepository
import javax.inject.Singleton

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 02.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

@Module
abstract class ApplicationModule {

    @Binds
    @Singleton
    abstract fun bindItemsRxRepository(networkItemsRxRepository: NetworkItemsRxRepository): ItemsRxRepository

}