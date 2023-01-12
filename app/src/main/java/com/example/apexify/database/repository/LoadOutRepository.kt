package com.example.apexify.database.repository

import androidx.annotation.WorkerThread
import com.example.apexify.Model.LoadOut
import com.example.apexify.database.dao.LoadOutDao
import kotlinx.coroutines.flow.Flow

class LoadOutRepository(private val loadOutDao: LoadOutDao) {

    val allLoadOuts: Flow<List<LoadOut>> = loadOutDao.getLoadouts()

    
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(loadOut: LoadOut) {
        loadOutDao.addLoadOut(loadOut)
    }
}