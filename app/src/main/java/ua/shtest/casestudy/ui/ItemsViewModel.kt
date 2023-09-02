package ua.shtest.casestudy.ui

import androidx.lifecycle.ViewModel
import timber.log.Timber
import ua.shtest.casestudy.domain.interactor.GetItemsUseCase
import ua.shtest.casestudy.domain.model.Item
import javax.inject.Inject

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 02.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

class ItemsViewModel @Inject constructor(private val getItemsUseCase: GetItemsUseCase) :
    ViewModel() {

    fun fetchItemsFromServer() {
        getItemsUseCase.invoke { either ->
            either.fold(::handleFailureWhileFetchingItems, ::handleSuccessfullyFetchedItems)
        }
    }

    private fun handleFailureWhileFetchingItems(throwable: Throwable) {

    }

    private fun handleSuccessfullyFetchedItems(items: List<Item>) {
        Timber.d("Successfully fetched: items = $items")
    }

    override fun onCleared() {
        getItemsUseCase.dispose()
        super.onCleared()
    }
}