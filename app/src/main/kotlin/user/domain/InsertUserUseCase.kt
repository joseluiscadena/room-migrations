package user.domain

import com.schibsted.android.core.scheduler.Scheduler
import com.schibsted.android.core.scheduler.extension.runOnIo
import user.data.repository.UserRepository

class InsertUserUseCase(private val userRepository: UserRepository, private val scheduler: Scheduler) {

    fun insertUser(accountId: String, userName: String, email: String) =
        userRepository.insertUser(accountId, userName, email).runOnIo(scheduler)
}
