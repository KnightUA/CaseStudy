package ua.shtest.casestudy.presentation.model.provider

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuProvider
import ua.shtest.casestudy.R
import ua.shtest.casestudy.presentation.model.menu.item.details.ItemDetailsScreenActionMenu
import ua.shtest.casestudy.presentation.model.menu.item.details.ItemDetailsScreenActionMenuHandler

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 03.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

class ItemDetailsScreenActionMenuProvider(
    private val actionMenuHandler: ItemDetailsScreenActionMenuHandler,
    private val editMode: Boolean
) :
    MenuProvider {
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        if (editMode) {
            menuInflater.inflate(R.menu.item_details, menu)
        }
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean = when (menuItem.itemId) {
        android.R.id.home -> {
            actionMenuHandler.onItemDetailsActionMenu(ItemDetailsScreenActionMenu.Back)
            true
        }

        R.id.menu_item_save -> {
            actionMenuHandler.onItemDetailsActionMenu(ItemDetailsScreenActionMenu.Save)
            true
        }

        else -> false
    }
}