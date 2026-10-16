package com.kart.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Configuration
public class JwtConfig {

    @Bean
    public JwtEncoder jwtEncoder(@Value("${app.jwt.secret}") String base64Secret) {
        byte[] keyBites;

        try {
            keyBites = Base64.getDecoder().decode(base64Secret);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("JWT_SECRET must contain at least 32 bytes");
        }

        SecretKey key = new SecretKeySpec(keyBites, "HmacSHA256");

        return NimbusJwtEncoder.withSecretKey(key).algorithm(MacAlgorithm.HS256).build();
    }
}
