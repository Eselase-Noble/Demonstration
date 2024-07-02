package org.nobleson.demonstration.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${application.security.jwt.secret_key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    private final static String SECRET_KEY = "";

    /**
     * Extract the username from the token
     * @param token
     * @return
     */
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    /**
     *
     * @param token
     * @param claimsTFunction
     * @return
     * @param <T>
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction){

        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);

    }

    /**
     * Generate the user token
     * @param extractClaims
     * @param userDetails
     * @return
     */
    public String generateToken(
        Map<String, Object> extractClaims,
                UserDetails userDetails){
        return buildToken(new HashMap<>(), userDetails, jwtExpiration);
    }


    public String generateToken(UserDetails userDetails){

        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generate refresh token
     * @param userDetails
     * @return
     */
    public String generateRefreshToken(UserDetails userDetails){
        return buildToken(new HashMap<>(), userDetails, refreshExpiration);
    }

    /**
     * Build a token
     * @param extractClaims
     * @param userDetails
     * @param expiration
     * @return
     */
    private String buildToken(Map<String,Object> extractClaims, UserDetails userDetails, long expiration){
        return Jwts.builder().setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    /**
     * Check if a token is still valid or not based on the username and if it has expired
     * @param token
     * @param userDetails
     * @return
     */
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Check whether a token has expired or not
     * @param token
     * @return
     */
    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extraction of token expiration from the token
     * @param token
     * @return
     */
    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extract all the claims from the token
     * @param token
     * @return
     */
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Get the signed keys
     * @return
     */
    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
