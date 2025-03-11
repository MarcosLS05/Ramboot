package Senet.Ramboot.service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


@Service
public class JWTService {

    @Value("${jwt.subject}")
    private String SUBJECT;
    @Value("${jwt.issuer}")
    private String ISSUER;
    @Value("${jwt.secret}")
    private String secretKey;

    private SecretKey getSecretKey() {    
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(Map<String, String> claims) {
        return Jwts.builder()
                .id(UUID.randomUUID().toString())
                .claims(claims)
                .subject(SUBJECT)
                .issuer(ISSUER)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 6000000))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)                
                .compact();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token).getBody();
    }

    public String validateToken(String sToken) {
        Claims oClaims = getAllClaimsFromToken(sToken);

        if (oClaims.getExpiration().before(new Date())) {
            return null;
        }

        if (oClaims.getIssuedAt().after(new Date())) {
            return null;
        }        

        if (!oClaims.getIssuer().equals(ISSUER)) {
            return null;
        }

        if (!oClaims.getSubject().equals(SUBJECT)) {
            return null;
        }
        
        return oClaims.get("email", String.class);

    }

}
