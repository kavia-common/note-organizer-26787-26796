package com.example.notesbackend.mapper;

import com.example.notesbackend.dto.NoteRequest;
import com.example.notesbackend.dto.NoteResponse;
import com.example.notesbackend.model.Note;

/**
 * Utility class for mapping between Note entity and API DTOs.
 */
public final class NoteMapper {

    private NoteMapper() {}

    public static Note toEntity(NoteRequest request) {
        return new Note(request.getUserId(), request.getTitle(), request.getContent());
    }

    public static void updateEntity(Note note, NoteRequest request) {
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        // userId is immutable in this simple design to prevent ownership changes via update
    }

    public static NoteResponse toResponse(Note note) {
        return new NoteResponse(
                note.getId(),
                note.getUserId(),
                note.getTitle(),
                note.getContent(),
                note.getCreatedAt(),
                note.getUpdatedAt()
        );
    }
}
