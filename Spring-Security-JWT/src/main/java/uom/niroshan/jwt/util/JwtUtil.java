package uom.niroshan.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
//to abstract all the jwt related stuffs
@Service
public class JwtUtil {

    private String SECRET_KEY = "secret";

    //takes in the token and return username--> and  to do this the extractClaim is used
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //takes in the token and return extractExpiration to do this the extractClaim is used
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
//this takes in a token then it uses a claim resolver in order to figure out the claims
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
//    generateToken method will create jwt based on the userdaetails. which means taking userName obiously
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    //this will call the jwt api that we added in pom.xml and it will create a token.subject here is the authenticated user
    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
