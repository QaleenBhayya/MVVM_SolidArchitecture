package com.startzplay.data.remote.search

data class SearchItem(
    val id: Int, var name: String?, val title: String?, val overview: String?,
    val poster_path: String?, val media_type: String?
)
