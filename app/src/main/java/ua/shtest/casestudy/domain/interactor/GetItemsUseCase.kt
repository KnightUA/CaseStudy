package ua.shtest.casestudy.domain.interactor

import io.reactivex.rxjava3.core.Observable
import ua.shtest.casestudy.domain.model.Item
import ua.shtest.casestudy.domain.repository.ItemsRxRepository
import javax.inject.Inject

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 02.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

class GetItemsUseCase @Inject constructor(
    private val repository: ItemsRxRepository,
) : UseCase<List<Item>>() {
    override fun execute(): Observable<List<Item>> {
        return repository.items().toObservable()
    }
}