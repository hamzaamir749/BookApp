package com.coderpakistan.bookapp.book.data.network

import com.coderpakistan.bookapp.book.data.dto.BookWorkDto
import com.coderpakistan.bookapp.book.data.dto.SearchResponseDto
import com.coderpakistan.bookapp.core.domain.DataError
import com.coderpakistan.bookapp.core.domain.Result

interface RemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchResponseDto, DataError.Remote>


    suspend fun getBookDetails(bookWorkId: String): Result<BookWorkDto, DataError.Remote>
}