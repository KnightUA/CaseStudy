package ua.shtest.casestudy.presentation.viewmodel.item

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ua.shtest.casestudy.presentation.model.menu.ItemListScreenActionMenu
import ua.shtest.casestudy.utils.SingleEvent
import javax.inject.Inject

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 03.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

class ItemHostViewModel @Inject constructor() : ViewModel() {

    private val _menuAction = MutableLiveData<SingleEvent<ItemListScreenActionMenu>>()
    val menuActionEvent: LiveData<SingleEvent<ItemListScreenActionMenu>> = _menuAction

    fun refreshMenuItemClicked() {
        _menuAction.postValue(SingleEvent(ItemListScreenActionMenu.Refresh))
    }
}