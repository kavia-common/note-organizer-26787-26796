package com.example.notesbackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * OpenAPI/Swagger configuration.
 * Applies Ocean Professional theme colors to Swagger UI via custom CSS.
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Notes API",
                version = "1.0.0",
                description = "Modern REST API for managing user notes.",
                contact = @Contact(name = "Notes Platform", url = "https://example.com", email = "support@example.com"),
                license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0")
        )
)
public class OpenApiConfig {

    // PUBLIC_INTERFACE
    /**
     * Provides additional OpenAPI customization for Swagger UI and API metadata.
     * - Sets external documentation pointer
     * - Registers a default server
     * - Adjusts Info metadata such as description and terms of service
     */
    @Bean
    public GlobalOpenApiCustomizer globalOpenApiCustomizer() {
        return openApi -> {
            openApi.setExternalDocs(new io.swagger.v3.oas.models.ExternalDocumentation()
                    .description("API Guide")
                    .url("https://example.com/docs"));
            openApi.setServers(List.of(new Server().url("/").description("Default Server")));

            // Use the model Info type (io.swagger.v3.oas.models.info.Info)
            io.swagger.v3.oas.models.info.Info modelInfo = openApi.getInfo();
            if (modelInfo != null) {
                // setSummary does not exist on model Info; adjust description to reflect theme/summary.
                String existingDescription = modelInfo.getDescription();
                String prefix = "Ocean Professional - Notes Service";
                if (existingDescription == null || existingDescription.isBlank()) {
                    modelInfo.setDescription(prefix);
                } else if (!existingDescription.startsWith(prefix)) {
                    modelInfo.setDescription(prefix + " — " + existingDescription);
                }
                modelInfo.setTermsOfService("https://example.com/terms");
            }
        };
    }
}
