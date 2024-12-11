package com.ochobits.optica.athentication.security.impl;

import com.ochobits.optica.athentication.models.User;
import com.ochobits.optica.athentication.security.interfaces.TokenGenerator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtTokenGeneratorImpl implements TokenGenerator {

    private final String jwtSecret;
    private final String companyName;
    private final Long jwtExpiration;

    public JwtTokenGeneratorImpl(
            @Value("${company.name}")
            String companyName,
            @Value("${security.jwt.key}")
            String jwtSecret,
            @Value("${security.jwt.expiration}")
            Long jwtExpiration
            ) {

        this.companyName = companyName;
        this.jwtSecret = jwtSecret;
        this.jwtExpiration = jwtExpiration;
    }

    @Override
    public String generate(User user) {
        return Jwts.builder()
                .issuer(companyName)
                .subject(user.getUserName())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+jwtExpiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    private Key getSigningKey(){
        byte[] secretByte = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(secretByte);
    }

}
