package com.pwc.assembly.solutions.engine.auth.proxy.model;

import java.util.List;
import java.util.UUID;

public record UserDetailsResponse(
        UUID userId,
        String firstName,
        String lastName,
        String email,
        boolean isApplicationAdmin,
        List<RoleResponse> roles
) {
}
