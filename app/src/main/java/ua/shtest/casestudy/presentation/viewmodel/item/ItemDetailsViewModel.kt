package ua.shtest.casestudy.presentation.viewmodel.item

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import ua.shtest.casestudy.domain.interactor.UpdateItemInDatabaseUseCase
import ua.shtest.casestudy.domain.model.Item
import ua.shtest.casestudy.presentation.model.menu.item.details.ItemDetailsScreenActionMenu
import ua.shtest.casestudy.presentation.model.menu.item.details.ItemDetailsScreenActionMenuHandler
import ua.shtest.casestudy.presentation.view.ui.fragments.item.ItemDetailsFragmentArgs
import ua.shtest.casestudy.utils.SingleEvent
import javax.inject.Inject

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 03.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

class ItemDetailsViewModel @Inject constructor(private val updateItemInDatabaseUseCase: UpdateItemInDatabaseUseCase) :
    ViewModel(), ItemDetailsScreenActionMenuHandler {

    private val _item = MutableLiveData<Item>()
    val item: LiveData<Item> = _item

    private val _editMode = MutableLiveData(false)
    val editMode: LiveData<Boolean> = _editMode

    private val _navigateUp = MutableLiveData<SingleEvent<Unit>>()
    val navigateUp: LiveData<SingleEvent<Unit>> = _navigateUp

    private var deviceName: String = ""

    val deviceNameTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            // empty
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            // empty
        }

        override fun afterTextChanged(editable: Editable?) {
            deviceName = editable.toString()
        }
    }

    override fun onItemDetailsActionMenu(actionMenu: ItemDetailsScreenActionMenu) {
        when (actionMenu) {
            ItemDetailsScreenActionMenu.Save -> saveItemDetails()
            ItemDetailsScreenActionMenu.Back -> _navigateUp.postValue(SingleEvent(Unit))
        }
    }

    fun fetchDataFromNavArgs(navArgs: ItemDetailsFragmentArgs) {
        _item.postValue(navArgs.item)
        _editMode.postValue(navArgs.editMode)
    }

    fun saveItemDetails() = item.value?.let {
        val updatedItem = it.copy(name = deviceName)
        updateItemInDatabaseUseCase.invoke(
            UpdateItemInDatabaseUseCase.Params(item = updatedItem)
        ) { either ->
            either.fold(onFailure = Timber::e, onSuccess = {
                _item.postValue(updatedItem)
                _editMode.postValue(false)
                _navigateUp.postValue(SingleEvent(Unit))
            })
        }
    }
}