package com.example.math_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Add_data_activity : AppCompatActivity() {

    lateinit var et_title : EditText
    lateinit var et_description : EditText
    lateinit var btn_save : Button
    lateinit var btn_cancel : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)

        supportActionBar?.title = "Add Note"


        et_title=findViewById(R.id.et_title)
        et_description=findViewById(R.id.et_description)
        btn_save = findViewById(R.id.btn_save)
        btn_cancel = findViewById(R.id.btn_cancel)

        btn_cancel.setOnClickListener(){
            finish() }
        btn_save.setOnClickListener(){
            saveNote()
        }
    }

    fun saveNote(){
        var title = et_title.text.toString()
        var description = et_description.text.toString()
        val intent = Intent()
        intent.putExtra("NoteTitle",title)
        intent.putExtra("NoteDescription",description)
        setResult(RESULT_OK,intent)
        finish()

    }
}