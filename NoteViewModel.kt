package com.example.math_game.NewViewModel


import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.math_game.Model.Note
import com.example.math_game.Repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    val myAllNotes : LiveData<List<Note>> = repository.myAllNotes.asLiveData()

   fun insert(note: Note) = viewModelScope .launch(Dispatchers.IO)
    {
        repository.insert(note)
    }
    fun update(note: Note) = viewModelScope .launch(Dispatchers.IO) {
        repository.update(note)
    }
    fun delete(note: Note) = viewModelScope .launch(Dispatchers.IO) {
        repository.delete(note)
    }
    fun deleteAllNotes() = viewModelScope .launch(Dispatchers.IO) {
        repository.deleteAllNotes()
    }

}

 class NoteViewModelFactory(private var repository: NoteRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(NoteViewModel::class.java))
        {
            return NoteViewModel(repository) as T
        }
        else{
            throw IllegalArgumentException("unknown View Model")
        }

    }
}




