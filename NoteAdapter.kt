package com.example.math_game.NoteAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.math_game.Model.Note
import com.example.math_game.R
import java.nio.file.Files.size

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.View_holder>() {

    var notes :List<Note> = ArrayList()
    class View_holder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
       val tv_title : TextView =itemView.findViewById(R.id.tv_title)
        val tv_desc : TextView =itemView.findViewById(R.id.tv_desc)
        val cardview :CardView =itemView.findViewById(R.id.cardview)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): View_holder {
        val view :View =LayoutInflater.from(parent.context).inflate(R.layout.each_note,parent,false)
        return View_holder(view)
    }

    override fun onBindViewHolder(holder: View_holder, position: Int) {
        var currnote : Note= notes[position]
       holder.tv_title.text = currnote.title.toString()
        holder.tv_desc.text= currnote.description.toString()


    }

    override fun getItemCount(): Int {

        return notes.size
    }

    fun setNote(myNotes : List<Note>)
    {
        this.notes = myNotes
        notifyDataSetChanged()
    }

    fun getNote(position:Int):Note
    {
        return notes[position]
    }

}