package com.internqwmlk.notesmaker.feature_note.domain.use_case

import com.internqwmlk.notesmaker.feature_note.domain.model.Note
import com.internqwmlk.notesmaker.feature_note.domain.repository.NoteRepository
import com.internqwmlk.notesmaker.feature_note.domain.util.NoteOrder
import com.internqwmlk.notesmaker.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes(private val noteRepository: NoteRepository) {

    operator fun invoke(noteOrder: NoteOrder=NoteOrder.Date(OrderType.Descending),date:Long): Flow<List<Note>> {
       return noteRepository.getNotes(date).map {notes->
           when(noteOrder.orderType){
               is OrderType.Ascending->{
                   when(noteOrder){
                       is NoteOrder.Title-> notes.sortedBy { it.title.lowercase() }

                       is NoteOrder.Date-> notes.sortedBy { it.timeStamp }

                       is NoteOrder.Color-> notes.sortedBy { it.color }



                   }
               }
               is OrderType.Descending -> {
                   when(noteOrder) {
                       is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                       is NoteOrder.Date -> notes.sortedByDescending { it.timeStamp }
                       is NoteOrder.Color -> notes.sortedByDescending { it.color}
                   }
               }
           }
       }


    }
}