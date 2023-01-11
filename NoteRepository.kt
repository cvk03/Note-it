package com.example.math_game.Repository

import androidx.annotation.WorkerThread
import com.example.math_game.Model.Note
import com.example.math_game.Room.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {

    val myAllNotes : Flow<List<Note>> = noteDao.getAllNotes()

    @WorkerThread
    suspend fun insert(note : Note)
    {
        noteDao.insert(note)
    }
    @WorkerThread
    suspend fun update(note : Note)
    {
        noteDao.update(note)
    }
    @WorkerThread
    suspend fun delete(note : Note)
    {
        noteDao.delete(note)
    }
    @WorkerThread
    suspend fun deleteAllNotes()
    {
        noteDao.deleteAllNotes()
    }
}