package com.abhirajput_19.youtube.hospitalManagement.security;

import com.abhirajput_19.youtube.hospitalManagement.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtService {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public JwtService(
            JwtEncoder jwtEncoder,
            JwtDecoder jwtDecoder) {

        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }

    public String generateToken(User user) {

        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(user.getEmail())
                .claim("role", user.getRole().name())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(60 * 60))
                .build();

        JwsHeader header = JwsHeader.with(
                MacAlgorithm.HS256
        ).build();

        return jwtEncoder.encode(
                JwtEncoderParameters.from(
                        header,
                        claims
                )
        ).getTokenValue();
    }

    public String extractEmail(String token) {

        Jwt jwt = jwtDecoder.decode(token);

        return jwt.getSubject();
    }

    public boolean isTokenValid(
            String token,
            UserDetails userDetails) {

        Jwt jwt = jwtDecoder.decode(token);

        return jwt.getSubject().equals(
                userDetails.getUsername()
        );
    }
}