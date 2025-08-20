package br.com.marinho.springsecurity_jwt.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.List;

public class JwtCreator {
    public  static final String HEADER_AUTHORIZATION = "Authorization";
    public  static final String ROLES_AUTHORITIES = "Authorization";

    public static String create(String prefix, String key, JwtObject jwtObject) {

        Key signingKey = Keys.hmacShaKeyFor(key.getBytes());

        String token = Jwts.builder()
                .subject(jwtObject.getSubject())
                .issuedAt(jwtObject.getIssuedAt())
                .expiration(jwtObject.getExpiration())
                .claim(ROLES_AUTHORITIES, checkRoles(jwtObject.getRoles()))
                .signWith(signingKey, SignatureAlgorithm.HS512)
                .compact();
        return prefix + " " + token;
    }

    public static JwtCreator create(String token, String prefix, String key)
            throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException {

        JwtObject object = new JwtObject();
        String tokenWithoutPrefix = token.replace(prefix, "");
//      String tokenWithoutPrefix = token.replace(prefix + " ", "");
        Key signingKey = Keys.hmacShaKeyFor(key.getBytes());

        Claims claims = Jwts.builder()
                .set
        return
    }

    private static Object checkRoles(List<String> roles) {
    }


}
