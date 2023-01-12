package com.example.apexify.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.apexify.Model.LoadOut
import kotlinx.coroutines.flow.Flow
@Dao
interface LoadOutDao {

    @Query("SELECT * FROM loadout")
    fun getLoadouts(): Flow<List<LoadOut>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLoadOut(loadout: LoadOut)

    @Query("DELETE FROM loadout")
    suspend fun deleteAll()

}