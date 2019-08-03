# Room Migration Example
The purpose of this project is to show how Room Migrations works. Through many scenarios a Room Migration is performed in order to preserve the data.

This project assumes that you have an existing app using Room Persistance Library or using SQLite managment in the old way, using SQLiteOpenHelper.

The first thing to do is to create JSON schema file of every database version of the app, following the next steps:
* Add the next code snippet to build.gradle app file inside defaultConfig section
```gradle
android {
    ...
    defaultConfig {
        ...
        javaCompileOptions {
            annotationProcessorOptions {
                arguments = ["room.schemaLocation":
                             "$projectDir/schemas".toString()]
            }
        }
    }
}
```
* Add the next code snippet to build.gradle app file inside android section
```gradle
android {
    ...
    sourceSets {
        androidTest.assets.srcDirs += files("$projectDir/schemas".toString())
    }
}
```
* Make the database exportSchema = true
```kotlin
@Database(entities = [(UserEntity::class)], version = DATABASE_USER_VERSION , exportSchema = true)
abstract class UserRoomDatabase : RoomDatabase() {
    ...
    ...
}
```
After that, rebuild the project and a new folder with the current JSON schema of the database is created in the project.
[![N|Solid](https://github.com/joseluiscadena/room-migrations/blob/master/resources/schema_screen.png?raw=true)](https://docs.google.com/presentation/d/151Mv_rdusqDgzs-_jMWgVabxYx79wxf1P4FlYoUXfOA/edit?usp=sharing)


The JSON output is something like this, and will be named with the number of database version:
```shell
app/schemas/user.data.datasource.room.UserRoomDatabase/1.json
```
```json
{
  "formatVersion": 1,
  "database": {
    "version": 1,
    "identityHash": "8bbd6c7d14eaf57a8bbccb306a9774f7",
    "entities": [
      {
        "tableName": "user",
        "createSql": "CREATE TABLE IF NOT EXISTS `${TABLE_NAME}` (`id` INTEGER NOT NULL, `account_id` TEXT NOT NULL, `username` TEXT NOT NULL, `email` TEXT NOT NULL, PRIMARY KEY(`id`))",
        "fields": [
          {
            "fieldPath": "id",
            "columnName": "id",
            "affinity": "INTEGER",
            "notNull": true
          },
          {
            "fieldPath": "accountId",
            "columnName": "account_id",
            "affinity": "TEXT",
            "notNull": true
          },
          {
            "fieldPath": "userName",
            "columnName": "username",
            "affinity": "TEXT",
            "notNull": true
          },
          {
            "fieldPath": "email",
            "columnName": "email",
            "affinity": "TEXT",
            "notNull": true
          }
        ],
        "primaryKey": {
          "columnNames": [
            "id"
          ],
          "autoGenerate": false
        },
        "indices": [],
        "foreignKeys": []
      }
    ],
    "setupQueries": [
      "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)",
      "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"8bbd6c7d14eaf57a8bbccb306a9774f7\")"
    ]
  }
}
```

WORK IN PROGRESS(CURRENTLY WORKING ON README DESCRIPTION)

![alt text](https://github.com/joseluiscadena/room-migrations/blob/master/resources/wip.jpg?raw=true)


[![N|Solid](https://github.com/joseluiscadena/room-migrations/blob/master/resources/slides.png?raw=true)](https://docs.google.com/presentation/d/151Mv_rdusqDgzs-_jMWgVabxYx79wxf1P4FlYoUXfOA/edit?usp=sharing)

# References
* https://developer.android.com/jetpack
* https://developer.android.com/topic/libraries/architecture/room
* https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
* https://proandroiddev.com/testing-the-un-testable-and-beyond-with-android-architecture-components-part-1-testing-room-4d97dec0f451
* https://proandroiddev.com/testing-the-un-testable-and-beyond-with-android-architecture-components-part-2-testing-room-2b956deabc00

# About the author
[![N|Solid](https://github.com/joseluiscadena/room-migrations/blob/master/resources/linkedin.png?raw=true?raw=true)](https://www.linkedin.com/in/joseluiscadena/)

# Edyen Products
[![N|Solid](https://github.com/joseluiscadena/room-migrations/blob/master/resources/edyen.jpg?raw=true)](http://edyen.com/)

[![N|Solid](https://github.com/joseluiscadena/room-migrations/blob/master/resources/xpressbus.jpg?raw=true)](https://www.xpress.city/)
