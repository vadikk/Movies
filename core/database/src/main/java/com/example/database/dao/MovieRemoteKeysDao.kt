package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.model.MovieRemoteKeys

@Dao
interface MovieRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<MovieRemoteKeys>)

    @Query("Select * From movie_remote_key Where movie_id = :id")
    suspend fun getRemoteKeyByMovieID(id: Int): MovieRemoteKeys?

    @Query("Delete From movie_remote_key")
    suspend fun clearRemoteKeys()

    @Query("Select created_at From movie_remote_key Order By created_at DESC LIMIT 1")
    suspend fun getCreationTime(): Long?
}