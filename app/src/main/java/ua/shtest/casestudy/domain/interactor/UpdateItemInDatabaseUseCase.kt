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

class UpdateItemInDatabaseUseCase @Inject constructor(
    private val itemDao: ItemDao,
    private val itemsMapper: ItemsMapper
) : UseCaseWithParameters<UpdateItemInDatabaseUseCase.Params, Int>() {

    override fun execute(params: Params): Observable<Int> {
        return itemDao.update(itemsMapper.map(params.item)).toObservable()
    }

    class Params(val item: Item)
}