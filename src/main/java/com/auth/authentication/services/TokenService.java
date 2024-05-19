package com.auth.authentication.services;

import java.time.Instant;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    private final JwtEncoder jwtEncoder;

    public TokenService(final JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }
    
    public String getToken(Authentication authentication) {
        Instant now = Instant.now();
        long expire = 15L;

        String username = authentication.getName();

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet
        .builder()
            .issuer("localhost")
            .issuedAt(now)
            .expiresAt(now.plusSeconds(expire))
            .subject(username)
            .claims((claims) -> {
                Set<String> roles = AuthorityUtils
                .authorityListToSet(authentication.getAuthorities())
                .stream()
                .collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
                
                claims.put("scope", roles);
            })
        .build();
        
        return jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }
}
