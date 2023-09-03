package ua.shtest.casestudy.ui.fragments.item

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import ua.shtest.casestudy.R

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 03.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

class ItemListFragment : Fragment(R.layout.fragment_item_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val controller = Navigation.findNavController(view)
        view.findViewById<View>(R.id.tv_test).setOnClickListener {
            controller.navigate(R.id.action_to_itemDetails)
        }
    }

}