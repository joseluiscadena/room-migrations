package roommigrations

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.roommigrations.R
import com.schibsted.android.core.view.StateData
import core.extension.liveDataObserve
import core.extension.viewModelProvidersOf
import core.framework.DaggerCompatSimpleActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.account_id_edit_text
import kotlinx.android.synthetic.main.activity_main.email_edit_text
import kotlinx.android.synthetic.main.activity_main.store_button
import kotlinx.android.synthetic.main.activity_main.username_edit_text
import roommigrations.viewmodel.UserViewModel
import timber.log.Timber
import javax.inject.Inject

class MainActivity : DaggerCompatSimpleActivity() {

    override fun layoutResId() = R.layout.activity_main

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initObservers()
        initStoreButton()
    }

    private fun initObservers() {
        userViewModel = viewModelProvidersOf(viewModelFactory)
        liveDataObserve(userViewModel.insertUserStateData, ::handleInsertUserStateData)
    }

    private fun initStoreButton() {
        store_button.setOnClickListener {
            userViewModel.insertUser(account_id_edit_text.text.toString(), username_edit_text.text.toString(),
                email_edit_text.text.toString())
        }
    }

    private fun handleInsertUserStateData(stateData: StateData?) {
        when (stateData) {
            is StateData.Loading -> { Timber.i("Loading") }
            is StateData.Complete -> { Timber.i("Complete") }
            is StateData.Error -> { Timber.i("Error") }
        }
    }
}
