package com.example.localdatastorage.data.repository

import com.example.localdatastorage.data.local.Book
import com.example.localdatastorage.data.local.BookDao

class BookRepository(private val dao: BookDao) {

    val books = dao.getAllBooks()

    suspend fun addBook(book: Book) {
        dao.insertBook(book)
    }
}
