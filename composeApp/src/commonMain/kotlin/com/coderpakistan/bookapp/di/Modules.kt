package com.coderpakistan.bookapp.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.coderpakistan.bookapp.book.data.database.DatabaseFactory
import com.coderpakistan.bookapp.book.data.database.FavouriteBookDatabase
import com.coderpakistan.bookapp.book.data.network.KtorRemoteBookDataSource
import com.coderpakistan.bookapp.book.data.network.RemoteBookDataSource
import com.coderpakistan.bookapp.book.data.repository.DefaultBookRepository
import com.coderpakistan.bookapp.book.domain.BookRepository
import com.coderpakistan.bookapp.book.presentation.SelectedBookViewModel
import com.coderpakistan.bookapp.book.presentation.book_details.BookDetailViewModel
import com.coderpakistan.bookapp.book.presentation.book_list.BookListViewModel
import com.coderpakistan.bookapp.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module


expect val platformModule: Module


val sharedModule = module {
    single {
        HttpClientFactory.create(get())
    }
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
    singleOf(::DefaultBookRepository).bind<BookRepository>()

    single {
        get<DatabaseFactory>().create().setDriver(BundledSQLiteDriver()).build()
    }

    single {
        get<FavouriteBookDatabase>().favouriteBookDao
    }

    viewModelOf(::BookListViewModel)
    viewModelOf(::SelectedBookViewModel)
    viewModelOf(::BookDetailViewModel)

}