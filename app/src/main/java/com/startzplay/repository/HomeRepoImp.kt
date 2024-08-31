package com.startzplay.repository

import com.startzplay.base.BaseRepo
import com.startzplay.common.APIStates
import com.startzplay.data.remote.search.SearchResponse
import com.startzplay.network.ApiService
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class HomeRepoImp @Inject constructor(
    private val apiService: ApiService
) : BaseRepo(), HomeRepo {

    override fun multiSearch(
        search: String, isAdult: Boolean, language: String, page: Int
    ): Flow<APIStates<SearchResponse>> {
        return flow {
            emit(genericApiResponse(apiService.multiSearch(search, isAdult, language, page)))
        }
    }

}