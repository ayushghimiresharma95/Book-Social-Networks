package com.example.BookNetworkServer.Security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
public class JwtService {
    
   @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;


    
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    public String generateToken(UserDetails userdetails){
        return generateToken( new HashMap<>(),userdetails) ;
    }
    public String generateToken(Map<String, Object> exrtaclaims, UserDetails userDetails){
        return buildToken(exrtaclaims, userDetails, jwtExpiration) ;
    }
    private String buildToken(Map<String, Object> claims,UserDetails userdetails,long jwtExpiration){
        var authorties = userdetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList() ;
        return Jwts.builder().setClaims(claims).setSubject(userdetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis()+jwtExpiration)).signWith(getSignInKey()).compact() ;
    }
    public boolean  isTokenValid(String token , UserDetails userDetails){
        String username = extractUsername(token) ;
        return (username.equals(userDetails.getUsername()) || isTokenValid(token));
    }
    public Boolean isTokenValid(String token){
        return extractExpiration(token).before(new Date()) ;
    }
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Key getSignInKey(){
         byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    
}
