package ua.shtest.casestudy.utils

import android.app.Application
import androidx.fragment.app.Fragment
import ua.shtest.casestudy.application.CaseStudyApplication

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 03.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

object ApplicationExtensions {
    fun Application.safeAppComponent() = (this as? CaseStudyApplication)?.appComponent
    fun Fragment.safeAppComponent() = requireActivity().application.safeAppComponent()
}