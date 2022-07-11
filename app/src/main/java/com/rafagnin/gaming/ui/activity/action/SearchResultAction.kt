package com.rafagnin.gaming.ui.activity.action

sealed class SearchResultAction {
    object Retry: SearchResultAction()
    data class Query(
        val text: String
    ) : SearchResultAction()
}
