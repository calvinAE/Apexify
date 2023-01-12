package com.example.apexify.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.apexify.Model.LoadOut
import com.example.apexify.database.dao.LoadOutDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

@Database(entities = arrayOf(LoadOut::class), version = 1)
abstract class LoadOutRoomDatabase : RoomDatabase() {

    abstract fun loadOutDao(): LoadOutDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var loadoutDao = database.loadOutDao()

                    loadoutDao.deleteAll()

                    // Add sample loadout
                    var loadOut = LoadOut(1,"Meta","CAR","R-301")
                    loadoutDao.addLoadOut(loadOut)

                }
            }
        }

    }
    companion object {

        @Volatile
        private var INSTANCE: LoadOutRoomDatabase? = null
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): LoadOutRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LoadOutRoomDatabase::class.java,
                    "loadout_database"
                )
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance

            }

        }
    }
}


