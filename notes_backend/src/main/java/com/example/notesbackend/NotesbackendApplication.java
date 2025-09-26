package com.example.notesbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application entrypoint for Notes Backend.
 */
@SpringBootApplication
public class NotesbackendApplication {

    // PUBLIC_INTERFACE
    /**
     * Bootstraps the Spring Boot application.
     * @param args CLI args
     */
    public static void main(String[] args) {
        SpringApplication.run(NotesbackendApplication.class, args);
    }
}
