package com.example.apexify

import android.app.Application
import com.example.apexify.database.LoadOutRoomDatabase
import com.example.apexify.database.repository.LoadOutRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ApexApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { LoadOutRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { LoadOutRepository(database.loadOutDao())}
}