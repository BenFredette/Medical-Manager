/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Helper.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.AbstractMap;
import java.util.Base64;
import java.util.Date;
import java.util.Map.Entry;
import static javax.crypto.Cipher.SECRET_KEY;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author student
 */
public class Authenticate {

    SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;;

    String secretString= "ThisIsMySecretStringForJWTAuthentication!";


    public String createJWT(String issuer, String subject, String account_type, long ttlMillis, String username, String password) {
    //The JWT signature algorithm we will be using to sign the token
    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretString);
    Key signingKey =new SecretKeySpec(Base64.getDecoder().decode(secretString), SignatureAlgorithm.HS256.getJcaName());

    // authenticate user
    Login login = new Login();
    boolean isAuthenticated = login.verifyLogin(username, password, account_type);
    if (!isAuthenticated) {
        return null;
    }

    //Let's set the JWT Claims
    Instant now = Instant.now();
    String jwtToken = Jwts.builder()
        .setIssuer(issuer)
        .setSubject(subject)
        .claim("account_type", account_type)
        .setIssuedAt(Date.from(now))
        .setExpiration(Date.from(now.plus(5l, ChronoUnit.MINUTES)))
        .signWith(signingKey)
        .compact();
    System.out.println(jwtToken);
    //Builds the JWT and serializes it to a compact, URL-safe string
    return jwtToken;
}


    public Entry<Boolean, String> verify(String jwt, String account_type) throws UnsupportedEncodingException {
    Jws<Claims> jws = null;
    String username = "";
    Key signingKey = new SecretKeySpec(Base64.getDecoder().decode(secretString), SignatureAlgorithm.HS256.getJcaName());
    System.out.println("I am verifying! :" + jwt);
    try {
        jws = Jwts.parserBuilder() // (1)
                .setSigningKey(signingKey) // (2)
                .build() // (3)
                .parseClaimsJws(jwt); // (4)

        System.out.println("We can safely trust the JWT");
        username = jws.getBody().getSubject();
        System.out.println(username);
        String jwtAccount_Type = jws.getBody().get("account_type", String.class);
        if (!account_type.equals(jwtAccount_Type)) {
            // User type mismatch
            Entry entry = new AbstractMap.SimpleEntry<Boolean, String>(false, "");
            return entry;
        }

    } catch (JwtException ex) {       // (5)
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println(ex.getMessage());
        System.out.println("We *cannot* use the JWT as intended by its creator");
    }
    if (jws == null) {
        Entry entry = new AbstractMap.SimpleEntry<Boolean, String>(false, "");
        return entry;
    }
    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);

    if (jws.getBody().getExpiration().before(now)) {
        Entry entry = new AbstractMap.SimpleEntry<Boolean, String>(false, "");
        return entry;
    }

    Entry entry = new AbstractMap.SimpleEntry<Boolean, String>(true, username);
    return entry;

}


}