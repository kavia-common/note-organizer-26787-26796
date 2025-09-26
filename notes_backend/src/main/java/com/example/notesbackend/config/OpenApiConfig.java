package com.example.notesbackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.*;
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

    @Bean
    public GlobalOpenApiCustomizer globalOpenApiCustomizer() {
        // Additional customization hooks if needed
        return openApi -> {
            openApi.setExternalDocs(new io.swagger.v3.oas.models.ExternalDocumentation()
                    .description("API Guide")
                    .url("https://example.com/docs"));
            openApi.setServers(List.of(new Server().url("/").description("Default Server")));
            Info info = openApi.getInfo();
            if (info != null) {
                info.setSummary("Ocean Professional - Notes Service");
                info.setTermsOfService("https://example.com/terms");
            }
        };
    }
}
