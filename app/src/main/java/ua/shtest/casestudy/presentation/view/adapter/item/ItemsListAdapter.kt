package ua.shtest.casestudy.presentation.view.adapter.item

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ua.shtest.casestudy.databinding.ListItemBinding
import ua.shtest.casestudy.domain.model.Item
import ua.shtest.casestudy.utils.ViewBindingExtension.viewBinding

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 03.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

class ItemsListAdapter : ListAdapter<Item, ItemViewHolder>(ItemDiffUtilCallback.newInstance()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent.viewBinding(ListItemBinding::inflate, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        getItem(position)?.let(holder::bindWith)
    }
}