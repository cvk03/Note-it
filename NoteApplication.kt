package com.example.math_game

import android.app.Application
import com.example.math_game.Repository.NoteRepository
import com.example.math_game.Room.NoteDatabase
import kotlinx.coroutines.InternalCoroutinesApi


class NoteApplication : Application() {

    @InternalCoroutinesApi
    val database by lazy { NoteDatabase.getDatabase(this) }
    @InternalCoroutinesApi
    val repository by lazy { NoteRepository(database.getNoteDao()) }

    
}