package ua.shtest.casestudy.domain.interactor

import io.reactivex.rxjava3.core.Observable
import ua.shtest.casestudy.data.local.dao.ItemDao
import ua.shtest.casestudy.data.local.mapper.ItemEntityMapper
import ua.shtest.casestudy.domain.model.Item
import javax.inject.Inject

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 02.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

class GetItemsFromDatabaseUseCase @Inject constructor(
    private val itemDao: ItemDao,
    private val itemEntityMapper: ItemEntityMapper
) : UseCase<List<Item>>() {
    override fun execute(): Observable<List<Item>> {
        return itemDao.getAll().map(itemEntityMapper::map).toObservable()
    }
}