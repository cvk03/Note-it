package com.example.math_game.View

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.math_game.Add_data_activity
import com.example.math_game.Model.Note
import com.example.math_game.NewViewModel.NoteViewModel
import com.example.math_game.NewViewModel.NoteViewModelFactory
import com.example.math_game.NoteAdapter.NoteAdapter
import com.example.math_game.NoteApplication
import com.example.math_game.R
import kotlinx.coroutines.InternalCoroutinesApi

class MainActivity : AppCompatActivity() {
    lateinit var rv : RecyclerView

    lateinit var noteViewModel :NoteViewModel

    lateinit var addActivityResultLauncher : ActivityResultLauncher<Intent>

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv= findViewById(R.id.rv)
        rv.layoutManager= LinearLayoutManager(this)
        val noteAdapter = NoteAdapter()
        rv.adapter = noteAdapter

        registerActivityResultLauncher()

        val viewModelFactory =NoteViewModelFactory((application as NoteApplication).repository)
         noteViewModel = ViewModelProvider(this,viewModelFactory).get(NoteViewModel::class.java)

        noteViewModel.myAllNotes.observe(this, Observer {
            notes-> noteAdapter.setNote(notes)
            //update UI
        })

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
               noteViewModel.delete(noteAdapter.getNote(viewHolder.adapterPosition))
            }
        }).attachToRecyclerView(rv)



    }

    fun registerActivityResultLauncher()
    {
        addActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback { resultAddNote->
            val resultCode = resultAddNote.resultCode
            val data = resultAddNote.data
            if(resultCode== RESULT_OK && data !=null)
            {
                val noteTitle : String =data.getStringExtra("NoteTitle").toString()
                val noteDes : String = data.getStringExtra("NoteDescription").toString()

                val note = Note(noteTitle,noteDes)
                noteViewModel.insert(note)
            }

        })
    }

    override fun onCreateOptionsMenu(the_menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu,the_menu)
        return true
    }

      override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.item_add_note->{
                var intent =Intent(this,Add_data_activity::class.java)
                addActivityResultLauncher.launch(intent)
            }
            R.id.item_deleteAllNotes->{
                dialogMsg()
            }
        }
        return true
    }

    fun dialogMsg(){
        var dialog = AlertDialog.Builder(this@MainActivity)
        dialog.setTitle("Delete")
            .setIcon(R.drawable.icon_delete_all_notes)
            .setMessage("Do you want to delete all notes?")
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel() })
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                noteViewModel.deleteAllNotes()
                Toast.makeText(this,"All notes deleted successfully!!",Toast.LENGTH_LONG).show()

            })
        dialog.create().show()
    }

}
