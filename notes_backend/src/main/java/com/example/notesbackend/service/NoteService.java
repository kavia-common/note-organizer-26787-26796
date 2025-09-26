package com.example.notesbackend.service;

import com.example.notesbackend.dto.NoteRequest;
import com.example.notesbackend.exception.NotFoundException;
import com.example.notesbackend.mapper.NoteMapper;
import com.example.notesbackend.model.Note;
import com.example.notesbackend.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    // PUBLIC_INTERFACE
    /**
     * Create a new note from request data.
     * @param request note request payload
     * @return saved Note entity
     */
    public Note create(NoteRequest request) {
        Note toSave = NoteMapper.toEntity(request);
        return repository.save(toSave);
    }

    // PUBLIC_INTERFACE
    /**
     * Get a note by its id or throw NotFoundException.
     * @param id note id
     * @return the Note entity
     */
    public Note getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Note with id " + id + " not found"));
    }

    // PUBLIC_INTERFACE
    /**
     * List notes for a user ordered by last update desc.
     * @param userId owner id
     * @return list of notes
     */
    public List<Note> listByUser(String userId) {
        return repository.findByUserIdOrderByUpdatedAtDesc(userId);
    }

    // PUBLIC_INTERFACE
    /**
     * Update an existing note's title/content. Validates the userId matches the note owner.
     * @param id note id
     * @param request note update payload
     * @return updated Note entity
     */
    @Transactional
    public Note update(Long id, NoteRequest request) {
        Note existing = getById(id);
        // security/ownership checks would go here; for now we ensure same user
        if (!existing.getUserId().equals(request.getUserId())) {
            throw new NotFoundException("Note not found for the given user");
        }
        NoteMapper.updateEntity(existing, request);
        return existing;
    }

    // PUBLIC_INTERFACE
    /**
     * Delete a note by id if it belongs to the given user.
     * @param id note id
     * @param userId owner id
     */
    public void delete(Long id, String userId) {
        Note existing = getById(id);
        if (!existing.getUserId().equals(userId)) {
            throw new NotFoundException("Note not found for the given user");
        }
        repository.deleteById(id);
    }
}
