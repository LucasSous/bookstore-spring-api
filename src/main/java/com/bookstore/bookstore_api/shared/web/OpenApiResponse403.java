package com.bookstore.bookstore_api.shared.web;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ApiResponse(
        responseCode = "403",
        description = "No permission to access the resource."
//        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDTO.class))
)
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OpenApiResponse403 {
}
