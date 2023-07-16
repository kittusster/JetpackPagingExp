package com.kiranvarghese.MachineTest.ui

import MyApi
import PassengersDataSource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class PassengersViewModel(
    private val api: MyApi
) : ViewModel() {
    val passengers =
        Pager(config = PagingConfig(pageSize = 10, prefetchDistance = 2), pagingSourceFactory = {
            PassengersDataSource(api)
        }).flow.cachedIn(viewModelScope)
}
