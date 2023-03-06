package com.example.database.utils

import androidx.room.withTransaction
import com.example.database.MoviesDatabase

class TransactionProvider(
    private val db: MoviesDatabase
) {
    suspend fun <T> runAsTransaction(block: suspend () -> T): T {
        return db.withTransaction(block)
    }
}