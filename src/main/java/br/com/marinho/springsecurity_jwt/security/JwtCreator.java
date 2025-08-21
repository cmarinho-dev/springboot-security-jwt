package br.com.marinho.springsecurity_jwt.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class JwtCreator {
    public  static final String HEADER_AUTHORIZATION = "Authorization";
    public  static final String ROLES_AUTHORITIES = "authorities";

    public static String create(String prefix, String secret, JwtObject jwtObject) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

        String token = Jwts.builder()
                .subject(jwtObject.getSubject())
                .issuedAt(jwtObject.getIssuedAt())
                .expiration(jwtObject.getExpiration())
                .claim(ROLES_AUTHORITIES, checkRoles(jwtObject.getRoles()))
                .signWith(key, Jwts.SIG.HS256)
                .compact();
        return prefix + " " + token;
    }

    public static JwtObject create(String token, String prefix, String secret)
            throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException {

        String tokenWithoutPrefix = token.replace(prefix, "").trim();

        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

        Jwt<?, Claims> jwt = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(tokenWithoutPrefix);

        Claims claims = jwt.getPayload();

        JwtObject object = new JwtObject();
        object.setSubject(claims.getSubject());
        object.setExpiration(claims.getExpiration());
        object.setIssuedAt(claims.getIssuedAt());
        object.setRoles(claims.get(ROLES_AUTHORITIES, String.class));

        return object;
    }

    private static Object checkRoles(List<String> roles) {
        return roles.stream().map(s -> "ROLE_".concat(s.replaceAll("ROLE_",""))).collect(Collectors.toList());
    }

}
