package com.example.math_game.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Note_table")
class Note (val title : String, val description : String){

    @PrimaryKey(autoGenerate = true)
    var id = 0



}