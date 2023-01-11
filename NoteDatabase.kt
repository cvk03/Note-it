package com.example.math_game.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.math_game.Model.Note
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Note::class],version = 1)
 abstract class NoteDatabase : RoomDatabase() {

     abstract fun getNoteDao() : NoteDao

     //singleton instance creation

     companion object{

         @Volatile
         private var INSTANCE : NoteDatabase? = null

         @InternalCoroutinesApi
         fun getDatabase(context: Context): NoteDatabase
         {
             return INSTANCE ?: synchronized(this)
             {
                 val instance = Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java,
                     "note_databse").build()
                 INSTANCE =instance
                 instance
             }
         }
     }

}