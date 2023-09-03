package ua.shtest.casestudy.domain.interactor

import io.reactivex.rxjava3.core.Observable
import ua.shtest.casestudy.data.local.dao.ItemDao
import ua.shtest.casestudy.domain.model.Item
import ua.shtest.casestudy.domain.model.mapper.ItemsMapper
import javax.inject.Inject

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 03.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

class SaveItemsIntoDatabaseUseCase @Inject constructor(
    private val itemDao: ItemDao,
    private val itemsMapper: ItemsMapper
) : UseCaseWithParameters<SaveItemsIntoDatabaseUseCase.Params, List<Long>>() {

    override fun execute(params: Params): Observable<List<Long>> {
        return itemDao.insertAll(*itemsMapper.map(params.items).toTypedArray()).toObservable()
    }

    class Params(val items: List<Item>)
}