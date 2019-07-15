package core.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import core.framework.Event
import core.framework.EventObserver


fun <T : Any, L : LiveData<T>> LifecycleOwner.liveDataObserve(liveData: L, body: (T?) -> Unit) =
        liveData.observe(this, Observer(body))

fun <T : Any, L : LiveData<Event<T>>> LifecycleOwner.liveEventObserve(liveData: L, onEventUnhandledContent: (T) -> Unit) =
        liveData.observe(this, EventObserver(onEventUnhandledContent))
