package ua.shtest.casestudy.presentation.view.adapter.item

import androidx.recyclerview.widget.DiffUtil
import ua.shtest.casestudy.domain.model.Item

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 03.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

class ItemDiffUtilCallback private constructor() : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.serialNumber == newItem.serialNumber
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }

    companion object {
        fun newInstance() = ItemDiffUtilCallback()
    }
}