package com.eadmin.user.service.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class JwtTokenServices {

    @Value("${jwt.token.secret}")
    private String key;

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 36000000; // 10h

    private final String rolesFieldName = "roles";
    private final String userId = "userId";
    private final String buildingId = "buildingId";
    private final String groupId = "groupId";

    // Creates a JWT token
    public String createToken(String username, List<String> roles, Long id, Long building, Long group) {
        // Add a custom field to the token
        Claims claims = Jwts.claims().setSubject(username);
        claims.put(rolesFieldName, roles);
        claims.put(userId, id);
        claims.put(buildingId, building);
        claims.put(groupId, group);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .compact();
    }

    public String createAdminToken(String username, List<String> roles, Long id) {
        // Add a custom field to the token
        Claims claims = Jwts.claims().setSubject(username);
        claims.put(rolesFieldName, roles);
        claims.put(userId, id);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .compact();
    }

    public String createAdministratorToken(String username, List<String> roles, Long id, Long group) {
        // Add a custom field to the token
        Claims claims = Jwts.claims().setSubject(username);
        claims.put(rolesFieldName, roles);
        claims.put(userId, id);
        claims.put(groupId, group);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .compact();
    }

    public String createPresidentToken(String username, List<String> roles, Long id, Long building) {
        // Add a custom field to the token
        Claims claims = Jwts.claims().setSubject(username);
        claims.put(rolesFieldName, roles);
        claims.put(userId, id);
        claims.put(buildingId, building);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .compact();
    }


    String getTokenFromRequest(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    // checks if the token is valid and not expired.
    boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(key.getBytes())).parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.debug("JWT token invalid " + e);
        }
        return false;
    }

    Authentication parseUserFromTokenInfo(String token) throws UsernameNotFoundException {
        Claims body = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(key.getBytes())).parseClaimsJws(token).getBody();
        String username = body.getSubject();
        List<String> roles = (List<String>) body.get(rolesFieldName);
        List<SimpleGrantedAuthority> authorities = new LinkedList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return new UsernamePasswordAuthenticationToken(username, "", authorities);
    }

}
