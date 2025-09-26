package com.example.notesbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Request payload for creating or updating a Note.
 */
@Schema(description = "Request body for creating or updating a note")
public class NoteRequest {

    @NotBlank
    @Size(max = 100)
    @Schema(description = "Identifier for the owner of this note", example = "user-123")
    private String userId;

    @NotBlank
    @Size(max = 200)
    @Schema(description = "Title of the note", example = "Grocery List")
    private String title;

    @Schema(description = "Content/body of the note", example = "- Apples\n- Bananas\n- Milk")
    private String content;

    public NoteRequest() {}

    public NoteRequest(String userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }
}
