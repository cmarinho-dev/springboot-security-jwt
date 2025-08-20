package br.com.marinho.springsecurity_jwt.security;

import io.jsonwebtoken.*;

import java.util.List;

public class JwtCreator {
    public  static final String HEADER_AUTHORIZATION = "Authorization";
    public  static final String ROLES_AUTHORITIES = "Authorization";

    public static String create(String prefix, String key, JwtObject jwtObject) {
        String token = Jwts.builder()
                .subject(jwtObject.getSubject())
                .issuedAt(jwtObject.getIssuedAt())
                .expiration(jwtObject.getExpiration())
                .claim(ROLES_AUTHORITIES, checkRoles(
                        jwtObject.getRoles()))
                .signWith("", SignatureAlgorithm.HS256).compact();
        return prefix + " " + token;
    }

    public static JwtCreator create(String token, String prefix, String key)
    throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException {
        JwtObject object = new JwtObject();
        token = token.replace(prefix, "");
        Claims claims = Jwts.parser().setSigningKey(key).parse
        return
    }

    private static Object checkRoles(List<String> roles) {
    }


}
