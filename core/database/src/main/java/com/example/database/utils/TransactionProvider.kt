package com.example.database.utils

import androidx.room.withTransaction
import com.example.database.MoviesDatabase
import javax.inject.Inject

class TransactionProvider @Inject constructor(
    private val db: MoviesDatabase
) {
    suspend fun <T> runAsTransaction(block: suspend () -> T): T {
        return db.withTransaction(block)
    }
}