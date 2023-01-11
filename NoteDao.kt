package com.example.math_game.Room

import androidx.room.*
import com.example.math_game.Model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("select * from note_table order by id asc")
    fun getAllNotes(): Flow<List<Note>>

    @Query("delete from Note_table")
    suspend fun deleteAllNotes()

}