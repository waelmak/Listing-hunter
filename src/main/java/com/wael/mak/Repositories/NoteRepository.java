package com.wael.mak.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wael.mak.Models.Note;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
	List<Note> findAll();
}
