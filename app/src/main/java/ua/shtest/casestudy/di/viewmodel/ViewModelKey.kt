package ua.shtest.casestudy.di.viewmodel

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 02.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)