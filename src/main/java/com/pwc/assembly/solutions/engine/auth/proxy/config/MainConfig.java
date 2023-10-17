package com.pwc.assembly.solutions.engine.auth.proxy.config;

import lombok.SneakyThrows;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.net.http.HttpClient;
import java.security.SecureRandom;

@Configuration
@EnableConfigurationProperties(BindingsProperties.class)
public class MainConfig {

    @Bean
    @SneakyThrows
    HttpClient httpClient() {
        SSLContext sslContext = SSLContext.getInstance("TLS");

        sslContext.init(null, new TrustManager[]{new SSLDisabler()}, new SecureRandom());

        return HttpClient.newBuilder()
                .sslContext(sslContext)
                .build();
    }
}
