package ua.shtest.casestudy.presentation.view.adapter.item

import androidx.recyclerview.widget.RecyclerView
import coil.load
import ua.shtest.casestudy.R
import ua.shtest.casestudy.databinding.ListItemBinding
import ua.shtest.casestudy.domain.model.Item
import ua.shtest.casestudy.presentation.model.provider.PlatformImageResourceProvider

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 03.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

class ItemViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindWith(item: Item) = with(binding) {
        ivDevice.load(PlatformImageResourceProvider.provideBy(platform = item.platform))
        tvDeviceName.text = item.name
        tvDeviceSerialNumber.text =
            root.resources.getString(R.string.format_serial_number, item.serialNumber.value)
    }
}