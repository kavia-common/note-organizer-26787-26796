package com.example.notesbackend.controller;

import com.example.notesbackend.dto.NoteRequest;
import com.example.notesbackend.dto.NoteResponse;
import com.example.notesbackend.mapper.NoteMapper;
import com.example.notesbackend.model.Note;
import com.example.notesbackend.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST endpoints for managing notes.
 */
@RestController
@RequestMapping(path = "/api/v1/notes", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Notes", description = "Operations related to Notes management")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    // PUBLIC_INTERFACE
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Create a note",
            description = "Creates a new note for the given user.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created",
                            content = @Content(schema = @Schema(implementation = NoteResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request")
            }
    )
    public ResponseEntity<NoteResponse> create(@Valid @RequestBody NoteRequest request) {
        /** Create a new note. */
        Note saved = service.create(request);
        NoteResponse resp = NoteMapper.toResponse(saved);
        return ResponseEntity.created(URI.create("/api/v1/notes/" + resp.getId())).body(resp);
    }

    // PUBLIC_INTERFACE
    @GetMapping("/{id}")
    @Operation(
            summary = "Get note by id",
            description = "Returns a single note by its identifier.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(schema = @Schema(implementation = NoteResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public ResponseEntity<NoteResponse> getById(
            @PathVariable("id") Long id
    ) {
        /** Retrieve a note by id. */
        Note note = service.getById(id);
        return ResponseEntity.ok(NoteMapper.toResponse(note));
    }

    // PUBLIC_INTERFACE
    @GetMapping
    @Operation(
            summary = "List notes for a user",
            description = "Returns all notes for the specified user, ordered by last update descending.",
            parameters = {
                    @Parameter(name = "userId", in = ParameterIn.QUERY, required = true, description = "User identifier")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK")
            }
    )
    public ResponseEntity<List<NoteResponse>> listByUser(
            @RequestParam("userId") String userId
    ) {
        /** List notes for a user. */
        List<NoteResponse> list = service.listByUser(userId)
                .stream()
                .map(NoteMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // PUBLIC_INTERFACE
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Update a note",
            description = "Updates the title and content of a note. The userId in the request must match the note owner.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(schema = @Schema(implementation = NoteResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public ResponseEntity<NoteResponse> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody NoteRequest request
    ) {
        /** Update a note. */
        Note updated = service.update(id, request);
        return ResponseEntity.ok(NoteMapper.toResponse(updated));
    }

    // PUBLIC_INTERFACE
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a note",
            description = "Deletes a note by id for the specified user. Requires userId as query parameter.",
            parameters = {
                    @Parameter(name = "userId", in = ParameterIn.QUERY, required = true, description = "User identifier")
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public ResponseEntity<Void> delete(
            @PathVariable("id") Long id,
            @RequestParam("userId") String userId
    ) {
        /** Delete a note. */
        service.delete(id, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
