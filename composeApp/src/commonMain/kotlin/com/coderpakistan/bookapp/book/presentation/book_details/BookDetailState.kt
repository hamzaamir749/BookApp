package com.coderpakistan.bookapp.book.presentation.book_details

import com.coderpakistan.bookapp.book.domain.Book

data class BookDetailState(
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
    val book: Book? = null
)