package com.coderpakistan.bookapp.book.data.repository

import androidx.sqlite.SQLiteException
import com.coderpakistan.bookapp.book.data.database.FavouriteBookDao
import com.coderpakistan.bookapp.book.data.mappers.toBook
import com.coderpakistan.bookapp.book.data.mappers.toBookEntity
import com.coderpakistan.bookapp.book.data.network.RemoteBookDataSource
import com.coderpakistan.bookapp.book.domain.Book
import com.coderpakistan.bookapp.book.domain.BookRepository
import com.coderpakistan.bookapp.core.domain.DataError
import com.coderpakistan.bookapp.core.domain.EmptyResult
import com.coderpakistan.bookapp.core.domain.Result
import com.coderpakistan.bookapp.core.domain.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource,
    private val favouriteBookDao: FavouriteBookDao
) :
    BookRepository {
    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource.searchBooks(query)
            .map { dto -> dto.results.map { it.toBook() } }
    }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError> {
        val localResult = favouriteBookDao.getFavouriteBook(bookId)
        return if (localResult == null) {
            remoteBookDataSource.getBookDetails(bookId).map { it.description }
        } else {
            Result.Success(localResult.description)
        }
    }

    override fun getFavoriteBooks(): Flow<List<Book>> {
        return favouriteBookDao.getFavouriteBooks().map { bookEntities ->
            bookEntities.map { it.toBook() }

        }
    }

    override fun isBookFavorite(id: String): Flow<Boolean> {
        return favouriteBookDao.getFavouriteBooks().map { bookEntities ->
            bookEntities.any { it.id == id }
        }
    }

    override suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local> {
        return try {
            favouriteBookDao.upsert(book.toBookEntity())
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFromFavorites(id: String) {
        favouriteBookDao.deleteFavouriteBook(id)
    }
}