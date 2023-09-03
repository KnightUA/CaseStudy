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

class ItemListAdapter : ListAdapter<Item, ItemViewHolder>(ItemDiffUtilCallback.newInstance()) {

    var onItemClickListener: OnItemClickListener? = null
    var onItemLongClickListener: OnItemLongClickListener? = null
    var onItemEditClickListener: OnItemEditClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            binding = parent.viewBinding(ListItemBinding::inflate, false),
            onItemClickListener = onItemClickListener,
            onItemLongClickListener = onItemLongClickListener,
            onItemEditClickListener = onItemEditClickListener,
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        getItem(position)?.let(holder::bindWith)
    }
}