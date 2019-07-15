package roommigrations.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.schibsted.android.core.view.StateData
import core.view.RxViewModel
import user.domain.InsertUserUseCase
import javax.inject.Inject

class UserViewModel @Inject constructor(private val insertUserUseCase: InsertUserUseCase) : RxViewModel() {

    private val _insertUserStateData = MutableLiveData<StateData>()
    val insertUserStateData: LiveData<StateData>
        get() = _insertUserStateData

    fun insertUser(accountId: String, userName: String, email: String) {
        _insertUserStateData.value = StateData.Loading
        val disposable = insertUserUseCase.insertUser(accountId, userName, email)
            .subscribe({ insertUserComplete() }, { insertUserError(it) })
        compositeDisposable.add(disposable)
    }

    private fun insertUserComplete() {
        _insertUserStateData.value = StateData.Complete
    }

    private fun insertUserError(throwable: Throwable) {
        _insertUserStateData.value = StateData.Error(throwable)
        throwable.printStackTrace()
    }
}
