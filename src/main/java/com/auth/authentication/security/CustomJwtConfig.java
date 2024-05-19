package com.auth.authentication.security;


import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtTimestampValidator;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import lombok.Getter;

@Configuration
@Getter
public class CustomJwtConfig {
    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;

    public CustomJwtConfig() throws NoSuchAlgorithmException {
        KeyPair keyPair = CustomRSAGenerator.keyPairGenerator();
        privateKey = (RSAPrivateKey) keyPair.getPrivate();
        publicKey = (RSAPublicKey) keyPair.getPublic();
    }   

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(publicKey).privateKey(privateKey).build();
        JWKSet jwkSet = new JWKSet(jwk);
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<SecurityContext>(jwkSet);
        NimbusJwtEncoder nimbusJwtEncoder = new NimbusJwtEncoder(jwks);
        return nimbusJwtEncoder;
    }

    @Bean
    JwtDecoder jwtDecoder() {
        JwtTimestampValidator jwtTimestampValidator = new JwtTimestampValidator(Duration.ofSeconds(1));
        OAuth2TokenValidator<Jwt> validatorRefresh = new DelegatingOAuth2TokenValidator<Jwt>(jwtTimestampValidator);

        NimbusJwtDecoder nimbusJwtDecoder = NimbusJwtDecoder.withPublicKey(publicKey).build();
        nimbusJwtDecoder.setJwtValidator(validatorRefresh);
        return nimbusJwtDecoder;
    }
}
