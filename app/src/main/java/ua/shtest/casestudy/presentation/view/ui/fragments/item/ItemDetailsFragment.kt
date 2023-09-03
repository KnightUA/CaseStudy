package ua.shtest.casestudy.presentation.view.ui.fragments.item

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import ua.shtest.casestudy.R
import ua.shtest.casestudy.databinding.FragmentItemDetailsBinding
import ua.shtest.casestudy.domain.model.Item
import ua.shtest.casestudy.presentation.model.provider.ItemDetailsScreenActionMenuProvider
import ua.shtest.casestudy.presentation.model.provider.PlatformImageResourceProvider
import ua.shtest.casestudy.presentation.viewmodel.item.ItemDetailsViewModel
import ua.shtest.casestudy.utils.ApplicationExtensions.safeAppComponent
import ua.shtest.casestudy.utils.EditTextExtensions.doOnActionDone
import ua.shtest.casestudy.utils.EditTextExtensions.hideKeyboard
import ua.shtest.casestudy.utils.EditTextExtensions.showKeyboard
import ua.shtest.casestudy.utils.ViewBindingExtension.viewBinding
import javax.inject.Inject

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 03.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

class ItemDetailsFragment : Fragment(R.layout.fragment_item_details) {

    private val binding by viewBinding(FragmentItemDetailsBinding::bind)
    private val navArgs by navArgs<ItemDetailsFragmentArgs>()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ItemDetailsViewModel> { viewModelFactory }
    private val menuProvider by lazy {
        return@lazy ItemDetailsScreenActionMenuProvider(
            viewModel,
            navArgs.editMode
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        safeAppComponent()?.inject(this)
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initObservers()
        initData()
    }

    override fun onDestroyView() {
        clearReferences()
        super.onDestroyView()
    }

    private fun initViews() {
        initBackPress()
        initMenuProvider()
    }

    private fun initObservers() {
        viewModel.item.observe(viewLifecycleOwner, ::bindItemWithUi)
        viewModel.editMode.observe(viewLifecycleOwner, ::updateViewsEditMode)
        viewModel.navigateUp.observe(viewLifecycleOwner) { findNavController().navigateUp() }
    }

    private fun initData() {
        viewModel.fetchDataFromNavArgs(navArgs)
    }

    private fun initBackPress() {
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun initMenuProvider() {
        (requireActivity() as MenuHost).addMenuProvider(menuProvider)
    }

    private fun updateViewsEditMode(editMode: Boolean) = with(binding) {
        tvDeviceName.isVisible = !editMode
        etDeviceName.isVisible = editMode

        etDeviceName.doOnActionDone(viewModel::saveItemDetails)
        etDeviceName.addTextChangedListener(viewModel.deviceNameTextWatcher).takeIf { editMode }
        etDeviceName.showKeyboard().takeIf { editMode }
    }

    private fun bindItemWithUi(item: Item) = with(binding) {
        ivDevice.load(PlatformImageResourceProvider.provideBy(item.platform))

        tvDeviceName.text = item.name
        etDeviceName.setText(item.name)

        tvDeviceSerialNumber.text =
            getString(R.string.format_serial_number, item.serialNumber.value)
        tvDeviceMacAddress.text =
            getString(R.string.format_mac_address, item.macAddress)

        tvDeviceFirmware.text =
            getString(R.string.format_firmware, item.firmware)
        tvDeviceModel.text =
            getString(R.string.format_model, item.model)
    }

    private fun clearReferences() {
        removeMenuProvider()
        removeBackButtonFromActionBar()

        binding.etDeviceName.hideKeyboard()
        binding.etDeviceName.removeTextChangedListener(viewModel.deviceNameTextWatcher)
    }

    private fun removeBackButtonFromActionBar() =
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(false)
        }

    private fun removeMenuProvider() {
        (requireActivity() as MenuHost).removeMenuProvider(menuProvider)
    }
}