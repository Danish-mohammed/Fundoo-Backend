package com.bridgelabz.notes.services;

import java.util.List;

import com.bridgelabz.notes.entities.Notes;
import org.springframework.stereotype.Service;

import com.bridgelabz.notes.payload.NotesDto;
@Service
public interface NotesService {
	
	//create
	Notes createNote(NotesDto notesDto, String userId);
	
	
	//update
	NotesDto updateNote(NotesDto notesDto,Integer notesId);
	
	//delete
	void deleteNote(Integer notesId);
	
	//get
	Notes getNote(Integer notesId);
	
	//getAll
	List<NotesDto> getAllNote();

	//getByUser
    List<Notes> getNoteByUser(Integer userId);

	Notes archiveNotes(int id);

	Notes moveToTrash(int id);

	Notes restoreFromTrash(int id);

	Notes unArchiveNotes(int id);
}
