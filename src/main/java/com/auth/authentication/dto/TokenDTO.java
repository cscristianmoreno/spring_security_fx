package com.auth.authentication.dto;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDTO {
    private String issuer;
    private String subject;
    private Instant expire;
    private Instant issuedAt;
    private String scope;
}
