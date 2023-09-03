package ua.shtest.casestudy.domain.model

import java.io.Serializable

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 02.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

data class ItemServerInfo(
    val device: String,
    val account: String,
    val event: String
) : Serializable