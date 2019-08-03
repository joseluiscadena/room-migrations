package user.data.datasource.room

import user.data.datasource.room.Query.SELECT_FROM

object UserConstants {
    const val DATABASE_USER = "user.db"
    const val DATABASE_USER_VERSION = 2
    const val TABLE_USER = "user"

    const val SELECT_USERS = SELECT_FROM + TABLE_USER
}

object Query {
    const val SELECT_FROM = "SELECT * FROM "
}
