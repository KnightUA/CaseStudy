package ua.shtest.casestudy.presentation.model.menu.item.details

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 03.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

sealed class ItemDetailsScreenActionMenu {
    object Save : ItemDetailsScreenActionMenu()
    object Back : ItemDetailsScreenActionMenu()
}