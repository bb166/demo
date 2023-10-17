package com.pwc.assembly.solutions.engine.auth.proxy.config;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Value
@ConfigurationProperties("bindings")
public class BindingsProperties {

    UserMgmtUrls userMgmt;

    @Value
    public static class UserMgmtUrls {
        String baseUrl;
        String usersMe;
    }
}
