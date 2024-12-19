package com.coderpakistan.bookapp.book.domain

import com.coderpakistan.bookapp.book.data.mappers.toBook
import com.coderpakistan.bookapp.core.domain.DataError
import com.coderpakistan.bookapp.core.domain.EmptyResult
import com.coderpakistan.bookapp.core.domain.Result
import com.coderpakistan.bookapp.core.domain.map
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
    suspend fun getBookDescription(bookId: String): Result<String?, DataError>

    fun getFavoriteBooks(): Flow<List<Book>>
    fun isBookFavorite(id: String): Flow<Boolean>

    suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local>
    suspend fun deleteFromFavorites(id: String)

}