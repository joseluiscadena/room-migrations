package roommigrations

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.roommigrations.R
import com.schibsted.android.core.view.StateData
import core.extension.liveDataObserve
import core.extension.viewModelProvidersOf
import core.framework.DaggerCompatSimpleActivity
import dagger.android.AndroidInjection
import roommigrations.viewmodel.UserViewModel
import javax.inject.Inject

class MainActivity : DaggerCompatSimpleActivity() {

    override fun layoutResId() = R.layout.activity_main

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var homeViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        homeViewModel = viewModelProvidersOf(viewModelFactory)
        liveDataObserve(homeViewModel.insertUserStateData, ::handleInsertUserStateData)
    }

    private fun handleInsertUserStateData(stateData: StateData?) {
        when (stateData) {
            is StateData.Loading -> { }
            is StateData.Complete -> { }
            is StateData.Error -> { }
        }
    }
}
