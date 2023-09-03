package ua.shtest.casestudy.presentation.view.ui.fragments.item

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ua.shtest.casestudy.R
import ua.shtest.casestudy.databinding.FragmentItemsHostBinding
import ua.shtest.casestudy.utils.ApplicationExtensions.safeAppComponent
import ua.shtest.casestudy.utils.ViewBindingExtension.viewBinding

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 03.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

class ItemHostFragment : Fragment(R.layout.fragment_items_host) {

    private val binding by viewBinding(FragmentItemsHostBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        safeAppComponent()?.inject(this)
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
    }
}