package user.data.datasource.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import user.data.datasource.room.UserConstants.SELECT_USERS

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userEntity: UserEntity)

    @Query(SELECT_USERS)
    fun getAll(): Single<List<UserEntity>>
}
