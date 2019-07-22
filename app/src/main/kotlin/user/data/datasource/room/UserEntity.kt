package user.data.datasource.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "account_id")
    val accountId: String,
    @ColumnInfo(name = "username")
    val userName: String,
    val email: String,
    val phone: String = ""
)
