package ua.shtest.casestudy.domain.repository

import io.reactivex.rxjava3.core.Single
import ua.shtest.casestudy.domain.model.Item

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 02.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

interface ItemsRxRepository {
    fun items(): Single<List<Item>>
}