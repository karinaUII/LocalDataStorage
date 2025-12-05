package com.example.localdatastorage.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Book::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {

                val callback = object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        CoroutineScope(Dispatchers.IO).launch {
                            INSTANCE?.bookDao()?.apply {
                                insertBook(Book(title = "Clean Code", author = "Robert C. Martin", year = 2008))
                                insertBook(Book(title = "Atomic Habits", author = "James Clear", year = 2018))
                                insertBook(Book(title = "The Pragmatic Programmer", author = "Andrew Hunt", year = 1999))
                                insertBook(Book(title = "Kotlin in Action", author = "Dmitry Jemerov", year = 2017))
                            }
                        }
                    }
                }

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "book_database_v2"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}