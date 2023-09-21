package com.bridgelabz.notes.repositories;

import java.util.List;

import com.bridgelabz.notes.entities.Notes;
import com.bridgelabz.notes.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepo extends JpaRepository<Notes, Integer>{
    List<Notes> findByUserId(int id);
}
