package com.pwc.assembly.solutions.engine.auth.proxy.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Slf4j
@RestControllerAdvice(assignableTypes = AuthorizationEndpoint.class)
public class AuthorizationEndpointExceptionHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(UNAUTHORIZED)
    void handleUnauthorized(Exception exception, HttpServletRequest request) {
        log.error("Token verification failed.", exception);
    }
}
