package com.startzplay.data.remote.search

data class SearchResponse(
    val page: Int, val total_pages: Int, val total_results: Int, val results: List<SearchItem>
)

