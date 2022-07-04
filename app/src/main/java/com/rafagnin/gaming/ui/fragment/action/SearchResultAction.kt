package com.rafagnin.gaming.ui.fragment.action

sealed class SearchResultAction {
    data class Query(
        val text: String
    ) : SearchResultAction()
}
