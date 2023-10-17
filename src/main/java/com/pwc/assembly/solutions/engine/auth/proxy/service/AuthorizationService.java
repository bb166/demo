package com.pwc.assembly.solutions.engine.auth.proxy.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pwc.assembly.solutions.engine.auth.proxy.config.BindingsProperties;
import com.pwc.assembly.solutions.engine.auth.proxy.model.UserDetailsResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final HttpClient httpClient;

    private final ObjectMapper objectMapper;

    private final BindingsProperties bindingsProperties;

    public UserDetailsResponse authorize(String authorizationHeaderValue, UUID workspaceId) {
        return getUserDataFromUserManagement(authorizationHeaderValue, workspaceId);
    }

    @SneakyThrows
    private UserDetailsResponse getUserDataFromUserManagement(String authorizationHeaderValue, UUID workspaceId) {
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(new URI(bindingsProperties.getUserMgmt().getUsersMe()))
                .GET()
                .header("Authorization", authorizationHeaderValue)
                .header("X-Workspace-Id", workspaceId.toString())
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        UserDetailsResponse parsedResponse = objectMapper.readValue(response.body(), UserDetailsResponse.class);

        return parsedResponse;
    }
}
