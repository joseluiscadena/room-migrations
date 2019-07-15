package user.data.datasource.room

import io.reactivex.Completable
import io.reactivex.Single

class UserRoomDataSource(private val userDao: UserDao) {

    fun insert(userEntity: UserEntity): Completable = Completable.fromAction { userDao.insert(userEntity) }

    fun getAll(): Single<List<UserEntity>> = userDao.getAll()
}
