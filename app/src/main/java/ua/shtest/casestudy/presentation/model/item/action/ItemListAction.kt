package ua.shtest.casestudy.presentation.model.item.action

import ua.shtest.casestudy.domain.model.Item

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 03.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

sealed class ItemListAction {
    data class ShowRemoveItemConfirmationDialog(val item: Item) : ItemListAction()
    data class OpenItemDetails(val item: Item, val editMode: Boolean = false) : ItemListAction()
}