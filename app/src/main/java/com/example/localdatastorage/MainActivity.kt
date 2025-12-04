package com.example.localdatastorage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.localdatastorage.data.local.AppDatabase
import com.example.localdatastorage.data.repository.BookRepository
import com.example.localdatastorage.ui.view.BookScreen
import com.example.localdatastorage.ui.viewmodel.BookViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao = AppDatabase.getDatabase(this).bookDao()
        val repository = BookRepository(dao)
        val viewModel = BookViewModel(repository)

        setContent {
            BookScreen(viewModel)
        }
    }
}
