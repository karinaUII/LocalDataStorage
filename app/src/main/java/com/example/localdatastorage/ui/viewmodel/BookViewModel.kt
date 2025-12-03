package com.example.localdatastorage.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.localdatastorage.data.local.Book
import com.example.localdatastorage.data.repository.BookRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BookViewModel(private val repository: BookRepository) : ViewModel() {

    val books: StateFlow<List<Book>> = repository.books
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun addBook(title: String, author: String, year: Int) {
        viewModelScope.launch {
            repository.addBook(
                Book(title = title, author = author, year = year)
            )
        }
    }
}
