package com.example.notesbackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Basic endpoints and convenience redirects.
 */
@RestController
@Tag(name = "Info", description = "Basic service information and docs redirect")
public class HelloController {

    // PUBLIC_INTERFACE
    /**
     * Welcome endpoint.
     * @return welcome message string
     */
    @GetMapping("/")
    @Operation(summary = "Welcome endpoint", description = "Returns a welcome message")
    public String hello() {
        return "Hello, Spring Boot! Welcome to notesbackend";
    }

    // PUBLIC_INTERFACE
    /**
     * Redirect to Swagger UI docs.
     * @return redirect view to /swagger-ui.html
     */
    @GetMapping("/docs")
    @Operation(summary = "API Documentation", description = "Redirects to Swagger UI")
    public RedirectView docs() {
        return new RedirectView("/swagger-ui.html");
    }

    // PUBLIC_INTERFACE
    /**
     * Basic health endpoint.
     * @return OK when app is healthy
     */
    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Returns application health status")
    public String health() {
        return "OK";
    }

    // PUBLIC_INTERFACE
    /**
     * Application info endpoint.
     * @return information string about the application
     */
    @GetMapping("/api/info")
    @Operation(summary = "Application info", description = "Returns application information")
    public String info() {
        return "Spring Boot Application: notesbackend";
    }
}
