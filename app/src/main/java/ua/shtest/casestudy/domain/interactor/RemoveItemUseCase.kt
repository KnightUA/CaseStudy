package ua.shtest.casestudy.domain.interactor

import io.reactivex.rxjava3.core.Observable
import ua.shtest.casestudy.domain.model.Item
import ua.shtest.casestudy.domain.repository.ItemsRxRepository
import javax.inject.Inject

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 03.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

class RemoveItemUseCase @Inject constructor(
    private val repository: ItemsRxRepository,
) : UseCaseWithParameters<RemoveItemUseCase.Params, List<Item>>() {

    override fun execute(params: Params): Observable<List<Item>> {
        return repository.items().toObservable()
    }

    class Params(protected val item: Item)
}