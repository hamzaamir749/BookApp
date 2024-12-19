package com.coderpakistan.bookapp.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.coderpakistan.bookapp.book.presentation.SelectedBookViewModel
import com.coderpakistan.bookapp.book.presentation.book_details.BookDetailAction
import com.coderpakistan.bookapp.book.presentation.book_details.BookDetailScreenRoot
import com.coderpakistan.bookapp.book.presentation.book_details.BookDetailViewModel
import com.coderpakistan.bookapp.book.presentation.book_list.BookListScreenRoot
import com.coderpakistan.bookapp.book.presentation.book_list.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.BookGraph
        ) {
            navigation<Route.BookGraph>(
                startDestination = Route.BookList
            ) {
                composable<Route.BookList>(
                    exitTransition = { slideOutHorizontally() },
                    popEnterTransition = {
                        slideInHorizontally()
                    }) { entry ->
                    val viewModel = koinViewModel<BookListViewModel>()
                    val selectedBookViewModel =
                        entry.sharedKoinViewModel<SelectedBookViewModel>(navController)
                    LaunchedEffect(true) {
                        selectedBookViewModel.onSelectBook(null)
                    }
                    BookListScreenRoot(
                        viewModel = viewModel,
                        onBookClick = { book ->
                            selectedBookViewModel.onSelectBook(book)
                            navController.navigate(Route.BookDetails(book.id))

                        }
                    )
                }
                composable<Route.BookDetails>(
                    enterTransition = {
                        slideInHorizontally() { initialOffset ->
                            initialOffset

                        }
                    },
                    popExitTransition = { slideOutHorizontally() { initialOffset ->
                        initialOffset

                    } }) { entry ->
                    val selectedBookViewModel =
                        entry.sharedKoinViewModel<SelectedBookViewModel>(navController)
                    val viewModel = koinViewModel<BookDetailViewModel>()
                    val selectedBook by selectedBookViewModel.selectedBook.collectAsStateWithLifecycle()

                    LaunchedEffect(selectedBook) {
                        selectedBook?.let { book ->
                            viewModel.onAction(BookDetailAction.OnSelectedBookChange(book))
                        }
                    }
                    BookDetailScreenRoot(
                        viewModel = viewModel, onBackClick = {
                            navController.navigateUp()
                        }
                    )
                }
            }
        }

    }
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}