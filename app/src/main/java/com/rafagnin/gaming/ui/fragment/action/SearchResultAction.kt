package com.rafagnin.gaming.ui.fragment.action

sealed class SearchResultAction {
    object Retry: SearchResultAction()
    data class Query(
        val text: String
    ) : SearchResultAction()
}
