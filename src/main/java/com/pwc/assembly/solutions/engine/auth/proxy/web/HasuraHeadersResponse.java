package com.pwc.assembly.solutions.engine.auth.proxy.web;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record HasuraHeadersResponse (
        @JsonProperty("X-Hasura-Role")
        String role,
        @JsonProperty("X-Hasura-User-Id")
        UUID userId,
        @JsonProperty("X-Hasura-Tenant-Id")
        UUID tenantId
) {
}
