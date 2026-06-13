package com.artraccoon.aelitafon.apps

data class AppSearchResult(
    val query: String,
    val matches: List<AppEntry>,
    val userMessage: String,
)
