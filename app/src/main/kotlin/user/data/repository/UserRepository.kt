package user.data.repository

import user.data.datasource.room.UserEntity
import user.data.datasource.room.UserRoomDataSource

class UserRepository(private val userRoomDataSource: UserRoomDataSource) {

    fun insertUser(accountId: String, userName: String, email: String) =
        userRoomDataSource.insert(UserEntity(accountId = accountId.toInt(), userName = userName, email = email,
            phone = "5554962458", address = "Paseo de la Reforma"))

    fun getUsers() = userRoomDataSource.getAll()
}
