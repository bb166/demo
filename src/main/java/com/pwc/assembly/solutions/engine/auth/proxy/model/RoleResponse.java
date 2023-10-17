package com.pwc.assembly.solutions.engine.auth.proxy.model;

import java.util.UUID;

public record RoleResponse(
        String name,
        UUID roleId
) {
}
