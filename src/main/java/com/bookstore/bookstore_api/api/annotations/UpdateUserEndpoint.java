package com.bookstore.bookstore_api.api.annotations;

import com.bookstore.bookstore_api.shared.web.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Operation(summary = "Update user")
@RequestMapping(method = RequestMethod.PUT, path = "/user/{id}", produces = "application/json")
@OpenApiResponse201
@OpenApiResponse400
@OpenApiResponse401
@OpenApiResponse403
@OpenApiResponse404
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UpdateUserEndpoint {
}
