package ua.shtest.casestudy.presentation.viewmodel.item

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import ua.shtest.casestudy.domain.interactor.GetItemsFromDatabaseUseCase
import ua.shtest.casestudy.domain.interactor.GetItemsFromServerUseCase
import ua.shtest.casestudy.domain.interactor.RemoveItemFromDatabaseUseCase
import ua.shtest.casestudy.domain.interactor.SaveItemsIntoDatabaseUseCase
import ua.shtest.casestudy.domain.model.Item
import ua.shtest.casestudy.presentation.model.item.action.ItemListAction
import ua.shtest.casestudy.presentation.model.item.states.ItemListScreenState
import ua.shtest.casestudy.presentation.model.menu.item.list.ItemListScreenActionMenu
import ua.shtest.casestudy.presentation.model.menu.item.list.ItemListScreenActionMenuHandler
import ua.shtest.casestudy.utils.SingleEvent
import javax.inject.Inject

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 02.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

class ItemsViewModel @Inject constructor(
    private val getItemsFromServerUseCase: GetItemsFromServerUseCase,
    private val getItemsFromDatabaseUseCase: GetItemsFromDatabaseUseCase,
    private val saveItemsIntoDatabaseUseCase: SaveItemsIntoDatabaseUseCase,
    private val removeItemFromDatabaseUseCase: RemoveItemFromDatabaseUseCase,
) :
    ViewModel(), ItemListScreenActionMenuHandler {

    private val _screenState = MutableLiveData<ItemListScreenState>(ItemListScreenState.Loading)
    val screenState: LiveData<ItemListScreenState> = _screenState

    private val _listAction = MutableLiveData<SingleEvent<ItemListAction>>()
    val listItemAction: LiveData<SingleEvent<ItemListAction>> = _listAction

    override fun onItemListActionMenu(actionMenu: ItemListScreenActionMenu) = when (actionMenu) {
        ItemListScreenActionMenu.Refresh -> fetchItemsFromServer()
    }

    fun fetchItems() {
        getItemsFromDatabaseUseCase.invoke { either ->
            either.fold(
                onFailure = { fetchItemsFromServer() },
                onSuccess = { _screenState.postValue(ItemListScreenState.Items(it)) })
        }
    }

    fun fetchItemsFromServer() {
        _screenState.postValue(ItemListScreenState.Loading)
        getItemsFromServerUseCase.invoke { either ->
            either.fold(::handleFailureWhileFetchingItems, ::handleSuccessfullyFetchedItems)
        }
    }

    private fun handleFailureWhileFetchingItems(throwable: Throwable) {
        _screenState.postValue(ItemListScreenState.Failed(throwable.localizedMessage))
    }

    private fun handleSuccessfullyFetchedItems(items: List<Item>) {
        saveItemsIntoDatabaseUseCase.invoke(SaveItemsIntoDatabaseUseCase.Params(items))
        _screenState.postValue(ItemListScreenState.Items(items))
    }

    fun showRemoveConfirmationDialog(item: Item) {
        _listAction.postValue(SingleEvent(ItemListAction.ShowRemoveItemConfirmationDialog(item)))
    }

    fun removeItemFromList(item: Item) {
        removeItemFromDatabaseUseCase.invoke(RemoveItemFromDatabaseUseCase.Params(item)) { either ->
            either.fold(onFailure = Timber::e, onSuccess = ::handleItemRemovedFromList)
        }
    }

    private fun handleItemRemovedFromList(result: Int) {
        getItemsFromDatabaseUseCase.invoke { either ->
            either.fold(onFailure = Timber::e, onSuccess = { items ->
                _screenState.postValue(ItemListScreenState.UpdatedItems(items))
            })
        }
    }

    fun openItemDetails(item: Item, editMode: Boolean = false) {
        _listAction.postValue(SingleEvent(ItemListAction.OpenItemDetails(item, editMode)))
    }

    override fun onCleared() {
        getItemsFromServerUseCase.dispose()
        super.onCleared()
    }
}