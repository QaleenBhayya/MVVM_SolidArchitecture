package com.startzplay.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startzplay.common.APIStates
import com.startzplay.data.remote.search.SearchResponse
import com.startzplay.repository.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val appRepo: HomeRepo) : ViewModel() {

    private val _searchData = MutableLiveData<APIStates<SearchResponse>>()
    val searchData: LiveData<APIStates<SearchResponse>> = _searchData

    fun  multiSearch(
        search: String, isAdult: Boolean, language: String, page: Int
    ) {
        viewModelScope.launch {
            _searchData.postValue(APIStates.waiting())
            appRepo.multiSearch(search,isAdult,language,page).collectLatest {
                _searchData.postValue(it)
            }
        }
    }
}