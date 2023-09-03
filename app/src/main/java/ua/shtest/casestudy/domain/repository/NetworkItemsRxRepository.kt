package ua.shtest.casestudy.domain.repository

import io.reactivex.rxjava3.core.Single
import ua.shtest.casestudy.data.remote.model.mapper.ItemsResponseMapper
import ua.shtest.casestudy.data.remote.service.ItemsService
import ua.shtest.casestudy.domain.model.Item
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 02.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

@Singleton
class NetworkItemsRxRepository @Inject constructor(
    private val itemsService: ItemsService,
    private val itemsResponseMapper: ItemsResponseMapper
) : ItemsRxRepository {
    override fun items(): Single<List<Item>> {
        return itemsService.fetchItems().map(itemsResponseMapper::map)
    }
}