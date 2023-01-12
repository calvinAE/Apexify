package com.example.apexify

import androidx.lifecycle.*
import com.example.apexify.Model.LoadOut
import com.example.apexify.database.repository.LoadOutRepository
import kotlinx.coroutines.launch

class LoadOutViewModel(private val repository: LoadOutRepository) : ViewModel() {


        val allLoaduts: LiveData<List<LoadOut>> = repository.allLoadOuts.asLiveData()

        /**
         * Launching a new coroutine to insert the data in a non-blocking way
         */
        fun insert(loadOut: LoadOut) = viewModelScope.launch {
                repository.insert(loadOut)
        }
}

class WordViewModelFactory(private val repository: LoadOutRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(LoadOutViewModel::class.java)) {
                        @Suppress("UNCHECKED_CAST")
                        return LoadOutViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
        }
}