package com.bookstore.bookstore_api.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI openAPI() {
        var openAPI = new OpenAPI();
        addInfo(openAPI);
        return openAPI;
    }

    private void addInfo(OpenAPI openAPI) {
        final String securitySchemeName = "bearerAuth";
        openAPI.info(
                new Info()
                        .title("Bookstore - API")
                        .version("0.1")
                        .description("This API exposes endpoints to Bookstore API."))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                );
    }
}
