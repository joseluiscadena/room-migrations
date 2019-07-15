package com.schibsted.android.core.view

sealed class StateData {
    object Loading : StateData()
    data class Success(var data: Any) : StateData() { inline fun <reified T> responseTo() = data as T }
    object Complete : StateData()
    data class Error(val error: Throwable) : StateData() { inline fun <reified T> errorTo() = error as T }
}
