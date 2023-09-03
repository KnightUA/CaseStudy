package ua.shtest.casestudy.presentation.viewmodel.item

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ua.shtest.casestudy.domain.interactor.GetItemsUseCase
import ua.shtest.casestudy.domain.model.Item
import ua.shtest.casestudy.presentation.model.item.action.ItemListAction
import ua.shtest.casestudy.presentation.model.item.states.ItemListScreenState
import ua.shtest.casestudy.presentation.model.menu.ItemListScreenActionMenu
import ua.shtest.casestudy.presentation.model.menu.ItemListScreenActionMenuHandler
import ua.shtest.casestudy.utils.SingleEvent
import javax.inject.Inject

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 02.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

class ItemsViewModel @Inject constructor(private val getItemsUseCase: GetItemsUseCase) :
    ViewModel(), ItemListScreenActionMenuHandler {

    private val _screenState = MutableLiveData<ItemListScreenState>(ItemListScreenState.Loading)
    val screenState: LiveData<ItemListScreenState> = _screenState

    private val _listAction = MutableLiveData<SingleEvent<ItemListAction>>()
    val listItemAction: LiveData<SingleEvent<ItemListAction>> = _listAction

    override fun onItemListActionMenu(actionMenu: ItemListScreenActionMenu) = when (actionMenu) {
        ItemListScreenActionMenu.Refresh -> fetchItemsFromServer()
    }

    fun fetchItemsFromServer() {
        _screenState.postValue(ItemListScreenState.Loading)
        getItemsUseCase.invoke { either ->
            either.fold(::handleFailureWhileFetchingItems, ::handleSuccessfullyFetchedItems)
        }
    }

    private fun handleFailureWhileFetchingItems(throwable: Throwable) {
        _screenState.postValue(ItemListScreenState.Failed(throwable.localizedMessage))
    }

    private fun handleSuccessfullyFetchedItems(items: List<Item>) {
        _screenState.postValue(ItemListScreenState.Items(items))
    }

    fun showRemoveConfirmationDialog(item: Item) {
        _listAction.postValue(SingleEvent(ItemListAction.ShowRemoveItemConfirmationDialog(item)))
    }

    fun removeItemFromList(item: Item) {

    }

    private fun handleItemRemovedFromList(items: List<Item>) {
        _screenState.postValue(ItemListScreenState.Items(items))
    }

    fun openItemDetails(item: Item, editMode: Boolean = false) {
        _listAction.postValue(SingleEvent(ItemListAction.OpenItemDetails(item, editMode)))
    }

    fun handleMenuActionEvent(menuActionEvent: SingleEvent<ItemListScreenActionMenu>) =
        menuActionEvent.getContentIfNotHandled()?.let { menuAction ->
            when (menuAction) {
                ItemListScreenActionMenu.Refresh -> fetchItemsFromServer()
            }
        }

    override fun onCleared() {
        getItemsUseCase.dispose()
        super.onCleared()
    }
}