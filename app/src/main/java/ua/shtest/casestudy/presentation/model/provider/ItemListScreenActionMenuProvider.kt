package ua.shtest.casestudy.presentation.model.provider

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuProvider
import ua.shtest.casestudy.R
import ua.shtest.casestudy.presentation.model.menu.item.list.ItemListScreenActionMenu
import ua.shtest.casestudy.presentation.model.menu.item.list.ItemListScreenActionMenuHandler

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 03.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

class ItemListScreenActionMenuProvider(private val actionMenuHandler: ItemListScreenActionMenuHandler) :
    MenuProvider {
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.item_list, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean = when (menuItem.itemId) {
        R.id.menu_item_refresh -> {
            actionMenuHandler.onItemListActionMenu(ItemListScreenActionMenu.Refresh)
            true
        }

        else -> false
    }
}