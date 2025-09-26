package com.example.notesbackend;

import com.example.notesbackend.dto.NoteRequest;
import com.example.notesbackend.model.Note;
import com.example.notesbackend.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NoteServiceTests {

    @Autowired
    private NoteService noteService;

    @Test
    void createAndFetchNote() {
        NoteRequest req = new NoteRequest("user-xyz", "Test Title", "Body");
        Note saved = noteService.create(req);
        Note fetched = noteService.getById(saved.getId());
        assertThat(fetched.getTitle()).isEqualTo("Test Title");
        assertThat(fetched.getUserId()).isEqualTo("user-xyz");
    }
}
