package ua.shtest.casestudy.data.remote.model.mapper

import ua.shtest.casestudy.data.remote.model.ItemsResponse
import ua.shtest.casestudy.domain.model.Item
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 02.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

@Singleton
class ItemsResponseMapper @Inject constructor(private val itemsApiMapper: ItemsApiMapper) {
    fun map(itemsResponse: ItemsResponse): List<Item> {
        return itemsApiMapper.map(itemsResponse.devices)
    }
}