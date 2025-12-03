package com.example.localdatastorage.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.localdatastorage.ui.viewmodel.BookViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookScreen(viewModel: BookViewModel) {

    val bookList by viewModel.books.collectAsState()

    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Judul Buku") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Penulis") },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )

        OutlinedTextField(
            value = year,
            onValueChange = { year = it },
            label = { Text("Tahun Terbit") },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )

        Button(
            onClick = {
                if (title.isNotEmpty() && author.isNotEmpty() && year.isNotEmpty()) {
                    viewModel.addBook(title, author, year.toInt())
                    title = ""
                    author = ""
                    year = ""
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            Text("Save / Add Book")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(bookList) { book ->
                Text(
                    "${book.title} - ${book.author} (${book.year})",
                    modifier = Modifier.padding(8.dp)
                )
                Divider()
            }
        }
    }
}
