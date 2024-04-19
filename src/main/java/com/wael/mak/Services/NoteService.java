package com.wael.mak.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wael.mak.Models.Listing;
import com.wael.mak.Models.Note;
import com.wael.mak.Repositories.NoteRepository;

@Service
public class NoteService {

	@Autowired
	private NoteRepository noteRepository;

	// Get all notes from DB
	public List<Note> allNotes() {
		return noteRepository.findAll();
	}

	// Create note
	public Note createNote(Note note) {
		return noteRepository.save(note);
	}

	// Find note by id
	public Note findNoteById(Long id) {
		Optional<Note> optionalNote = noteRepository.findById(id);
		return (optionalNote.isPresent()) ? optionalNote.get() : null;
	}

	// Update Note
	public Note updateNote(Note n) {
		return noteRepository.save(n);
	}

	// Delete Note
	public void deleteNoteById(Long id) {
		noteRepository.deleteById(id);
	}

	// Delete Notes
	public void deleteNotes(List<Note> notes) {
		noteRepository.deleteAll(notes);
		;
	}
}
