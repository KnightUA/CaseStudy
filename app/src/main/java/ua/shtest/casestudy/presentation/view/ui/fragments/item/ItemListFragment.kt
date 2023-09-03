package ua.shtest.casestudy.presentation.view.ui.fragments.item

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ua.shtest.casestudy.R
import ua.shtest.casestudy.databinding.FragmentItemListBinding
import ua.shtest.casestudy.domain.model.Item
import ua.shtest.casestudy.presentation.model.states.item.ItemListScreenState
import ua.shtest.casestudy.presentation.view.adapter.item.ItemsListAdapter
import ua.shtest.casestudy.presentation.viewmodel.item.ItemsViewModel
import ua.shtest.casestudy.utils.ApplicationExtensions.safeAppComponent
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

    val viewModel: ItemsViewModel by viewModels { viewModelFactory }

    private val binding by viewBinding(FragmentItemListBinding::bind)
    private val itemsListAdapter by lazy {
        return@lazy ItemsListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        safeAppComponent()?.inject(this)

        initViews()
        initObservers()
        viewModel.fetchItemsFromServer()
    }

    private fun initViews() {
        initRecyclerAdapter()
    }

    private fun initObservers() {
        viewModel.screenState.observe(viewLifecycleOwner, ::handleScreenState)
    }

    private fun initRecyclerAdapter() = with(binding.rvItems) {
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = itemsListAdapter
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

    private fun showLoading() = with(binding) {
        rvItems.isVisible = false
        tvErrorMessage.isVisible = false
        progressBar.isVisible = true
    }

    private fun showItems(items: List<Item>) = with(binding) {
        tvErrorMessage.isVisible = false
        progressBar.isVisible = false
        rvItems.isVisible = true

        itemsListAdapter.submitList(items)
    }

    private fun updateItems(items: List<Item>) = itemsListAdapter.submitList(items)

    private fun showError(message: CharSequence? = null) = with(binding) {
        progressBar.isVisible = false
        rvItems.isVisible = false
        tvErrorMessage.isVisible = true

        tvErrorMessage.text = message ?: getString(R.string.general_error_message)
    }
}