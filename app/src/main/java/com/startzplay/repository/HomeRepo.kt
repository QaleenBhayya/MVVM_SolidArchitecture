package com.startzplay.repository

import com.startzplay.common.APIStates
import com.startzplay.data.remote.search.SearchResponse
import kotlinx.coroutines.flow.Flow

interface HomeRepo {


    fun multiSearch(
        search: String, isAdult: Boolean, language: String, page: Int
    ): Flow<APIStates<SearchResponse>>
}