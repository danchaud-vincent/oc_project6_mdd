package com.orion.mdd.security.jwt;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;

import com.orion.mdd.model.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${oc.app.jwtSecret}")
    private String jwtSecret;

    @Value("${oc.app.jwtExpirationMs}")
    private Integer jwtExpirationMs;

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public String generateJwtToken(User user) {

        // time
        Instant now = Instant.now();
        Instant expiry = now.plusMillis(jwtExpirationMs);

        // claims
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("mdd-api")
                .issuedAt(now)
                .expiresAt(expiry)
                .subject(user.getEmail())
                .build();

        // Params
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS256).build(),
                claims);

        return jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    }

    public String getUserNameFromJwtToken(String token) {
        try {
            Jwt jwt = jwtDecoder.decode(token);
            return jwt.getSubject();
        } catch (JwtException e) {
            logger.error("Cannot extract username from token: {}", e.getMessage());
            return null;
        }
    }

}
