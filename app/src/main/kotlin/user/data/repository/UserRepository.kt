package user.data.repository

import user.data.datasource.room.UserEntity
import user.data.datasource.room.UserRoomDataSource

class UserRepository(private val userRoomDataSource: UserRoomDataSource) {

    fun insertUser(accountId: String, userName: String, email: String) =
        userRoomDataSource.insert(UserEntity(accountId = accountId, userName = userName, email = email))

    fun getUsers() = userRoomDataSource.getAll()
}
