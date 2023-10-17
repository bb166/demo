package com.pwc.assembly.solutions.engine.auth.proxy.web;

import com.pwc.assembly.solutions.engine.auth.proxy.model.RoleResponse;
import com.pwc.assembly.solutions.engine.auth.proxy.model.UserDetailsResponse;
import com.pwc.assembly.solutions.engine.auth.proxy.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthorizationEndpoint {

    private final AuthorizationService authorizationService;

    @GetMapping("/authorize")
    public ResponseEntity<HasuraHeadersResponse> authorize(
            @RequestHeader("Authorization") String token,
            @RequestHeader("X-Workspace-Id") UUID workspaceId
    ) {
        log.info("TOKEN ->> " + token);
        log.info("WorkspaceId ->> " + workspaceId);
        UserDetailsResponse userDetailsResponse = authorizationService.authorize(token, workspaceId);

        log.info("Response ->> " + userDetailsResponse);
        return ResponseEntity.ok(
                new HasuraHeadersResponse(
                        userDetailsResponse.roles().stream().findAny().map(RoleResponse::name).orElse("anonymous"),
                        userDetailsResponse.userId(),
                        workspaceId
                )
        );
    }
}
