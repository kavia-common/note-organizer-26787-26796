package com.example.notesbackend.repository;

import com.example.notesbackend.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// PUBLIC_INTERFACE
/**
 * Repository for Note entities.
 */
public interface NoteRepository extends JpaRepository<Note, Long> {
    /**
     * Find notes by user id ordered by last update descending.
     * @param userId owner id
     * @return list of notes
     */
    List<Note> findByUserIdOrderByUpdatedAtDesc(String userId);
}
