package com.example.notesbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

/**
 * Response payload representing a Note.
 */
@Schema(description = "Note object returned by the API")
public class NoteResponse {

    @Schema(description = "Unique identifier of the note", example = "1")
    private Long id;

    @Schema(description = "Identifier for the owner of this note", example = "user-123")
    private String userId;

    @Schema(description = "Title of the note", example = "Grocery List")
    private String title;

    @Schema(description = "Content/body of the note", example = "- Apples\n- Bananas\n- Milk")
    private String content;

    @Schema(description = "Creation timestamp (UTC)", example = "2024-05-10T12:34:56Z")
    private Instant createdAt;

    @Schema(description = "Last update timestamp (UTC)", example = "2024-05-10T13:00:00Z")
    private Instant updatedAt;

    public NoteResponse() {}

    public NoteResponse(Long id, String userId, String title, String content, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public Instant getCreatedAt() { return createdAt; }

    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
