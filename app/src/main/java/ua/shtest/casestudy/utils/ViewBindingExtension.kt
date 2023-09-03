package ua.shtest.casestudy.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 03.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

object ViewBindingExtension {
    inline fun <T : ViewBinding> ViewGroup.viewBinding(
        factory: (LayoutInflater, ViewGroup, Boolean) -> T,
        attachToRoot: Boolean = true
    ) = factory(LayoutInflater.from(context), this, attachToRoot)

    inline fun <T : ViewBinding> ViewGroup.viewBinding(
        factory: (LayoutInflater, ViewGroup) -> T
    ) = factory(LayoutInflater.from(context), this)

    fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
        ViewBindingDelegate(this, viewBindingFactory)

}