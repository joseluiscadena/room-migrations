package core.provider.resource

import android.content.Context
import androidx.annotation.StringRes

class ResourceProvider constructor(private val context: Context) {

    fun getString(@StringRes id: Int): String = context.getString(id)
}
