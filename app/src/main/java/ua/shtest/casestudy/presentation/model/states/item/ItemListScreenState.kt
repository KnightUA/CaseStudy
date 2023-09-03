package ua.shtest.casestudy.presentation.model.states.item

import ua.shtest.casestudy.domain.model.Item

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 03.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

sealed class ItemListScreenState {
    object Loading : ItemListScreenState()
    data class Failed(val errorMessage: String? = null) : ItemListScreenState()
    data class Items(val values: List<Item>) : ItemListScreenState()
    data class UpdatedItems(val values: List<Item>) : ItemListScreenState()
}
