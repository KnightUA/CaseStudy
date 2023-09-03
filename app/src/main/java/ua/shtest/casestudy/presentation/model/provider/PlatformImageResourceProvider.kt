package ua.shtest.casestudy.presentation.model.provider

import ua.shtest.casestudy.R
import ua.shtest.casestudy.domain.model.Platform

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 03.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

object PlatformImageResourceProvider {
    fun provideBy(platform: Platform) = when (platform) {
        Platform.SercommG450 -> R.drawable.vera_plus_big
        Platform.SercommG550 -> R.drawable.vera_secure_big
        else -> R.drawable.vera_edge_big
    }
}