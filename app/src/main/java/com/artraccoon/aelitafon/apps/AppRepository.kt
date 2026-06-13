package com.artraccoon.aelitafon.apps

interface AppRepository {
    fun getLaunchableApps(): List<AppEntry>
    fun searchApps(query: String): AppSearchResult
}
