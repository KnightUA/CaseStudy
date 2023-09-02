package ua.shtest.casestudy.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ua.shtest.casestudy.R
import ua.shtest.casestudy.application.CaseStudyApplication
import javax.inject.Inject

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 02.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

class SingleActivity : AppCompatActivity(R.layout.activity_single) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val mViewModel: ItemsViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as? CaseStudyApplication)?.appComponent?.inject(this)
        super.onCreate(savedInstanceState)

        mViewModel.fetchItemsFromServer()
    }
}