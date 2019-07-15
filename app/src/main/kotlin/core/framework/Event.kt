package core.framework

data class Event<out T>(private val content: T) {

    private var hasBeenHandled = false

    fun getContentIfNotHandled(): T? =
            if (!hasBeenHandled) {
                hasBeenHandled = true
                content
            } else null

    fun peekContent(): T = content
}
