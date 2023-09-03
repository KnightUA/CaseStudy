package ua.shtest.casestudy.presentation.view.ui.fragments.item

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuHost
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ua.shtest.casestudy.R
import ua.shtest.casestudy.databinding.FragmentItemListBinding
import ua.shtest.casestudy.domain.model.Item
import ua.shtest.casestudy.presentation.model.item.action.ItemListAction
import ua.shtest.casestudy.presentation.model.item.states.ItemListScreenState
import ua.shtest.casestudy.presentation.model.provider.ItemListScreenActionMenuProvider
import ua.shtest.casestudy.presentation.view.adapter.item.ItemListAdapter
import ua.shtest.casestudy.presentation.viewmodel.item.ItemsViewModel
import ua.shtest.casestudy.utils.ApplicationExtensions.safeAppComponent
import ua.shtest.casestudy.utils.SingleEvent
import ua.shtest.casestudy.utils.ViewBindingExtension.viewBinding
import javax.inject.Inject

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 03.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

class ItemListFragment : Fragment(R.layout.fragment_item_list) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: ItemsViewModel by viewModels { viewModelFactory }

    private val binding by viewBinding(FragmentItemListBinding::bind)
    private val menuProvider by lazy { return@lazy ItemListScreenActionMenuProvider(viewModel) }
    private val itemListAdapter by lazy {
        return@lazy ItemListAdapter().apply {
            onItemClickListener = { viewModel.openItemDetails(it) }
            onItemLongClickListener = viewModel::showRemoveConfirmationDialog
            onItemEditClickListener = { viewModel.openItemDetails(it, editMode = true) }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        safeAppComponent()?.inject(this)
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initObservers()
        initData()
    }

    override fun onDestroyView() {
        removeMenuProvider()
        super.onDestroyView()
    }

    private fun initViews() {
        initMenuProvider()
        initRecyclerAdapter()
    }

    private fun initObservers() {
        with(viewModel) {
            screenState.observe(viewLifecycleOwner, ::handleScreenState)
            listItemAction.observe(viewLifecycleOwner, ::handleItemListActionEvent)
        }
    }

    private fun initData() {
        viewModel.fetchItems()
    }

    private fun initMenuProvider() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(menuProvider)
    }

    private fun initRecyclerAdapter() = with(binding.rvItems) {
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = itemListAdapter
        addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    private fun handleScreenState(screenState: ItemListScreenState) {
        when (screenState) {
            is ItemListScreenState.Failed -> showError(screenState.errorMessage)
            is ItemListScreenState.Items -> showItems(screenState.values)
            is ItemListScreenState.UpdatedItems -> updateItems(screenState.values)
            ItemListScreenState.Loading -> showLoading()
        }
    }

    private fun handleItemListActionEvent(listItemActionEvent: SingleEvent<ItemListAction>) {
        listItemActionEvent.getContentIfNotHandled()?.let { action ->
            when (action) {
                is ItemListAction.OpenItemDetails -> navigateToItemDetails(
                    action.item,
                    action.editMode
                )

                is ItemListAction.ShowRemoveItemConfirmationDialog -> showRemoveItemConfirmationDialog(
                    action.item
                )
            }
        }
    }

    private fun showLoading() = with(binding) {
        rvItems.isVisible = false
        tvErrorMessage.isVisible = false
        progressBar.isVisible = true
    }

    private fun showItems(items: List<Item>) = with(binding) {
        tvErrorMessage.isVisible = false
        progressBar.isVisible = false
        rvItems.isVisible = true

        itemListAdapter.submitList(items)
    }

    private fun updateItems(items: List<Item>) = itemListAdapter.submitList(items)

    private fun showError(message: CharSequence? = null) = with(binding) {
        progressBar.isVisible = false
        rvItems.isVisible = false
        tvErrorMessage.isVisible = true

        tvErrorMessage.text = message ?: getString(R.string.general_error_message)
    }

    private fun navigateToItemDetails(item: Item, editMode: Boolean) {
        findNavController().navigate(ItemListFragmentDirections.actionToItemDetails(item, editMode))
    }

    private fun showRemoveItemConfirmationDialog(item: Item) {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.dialog_remove_item_title))
            .setMessage(getString(R.string.dialog_remove_item_message, item.name))
            .setPositiveButton(getString(R.string.dialog_remove_item_positive_button)) { dialog, _ ->
                viewModel.removeItemFromList(item)
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.dialog_remove_item_negative_button)) { dialog, _ -> dialog.dismiss() }
            .create().show()
    }

    private fun removeMenuProvider() {
        val menuHost: MenuHost = requireActivity()
        menuHost.removeMenuProvider(menuProvider)
    }
}