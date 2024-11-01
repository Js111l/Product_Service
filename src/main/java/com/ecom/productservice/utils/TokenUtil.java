package com.ecom.productservice.utils;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
//    @Value("${jwt.secret}")
//    private String secret;
//    @Value("${jwt.salt}")
//    private String salt;

    String secret = "hsef";
    String salt = "fsj98";
    private static final Integer ITERATION_COUNT = 10_000;
    private static final Integer KEY_SIZE = 256;

    public String createUserToken(Map<String, String> claims, String email, String userId) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.builder()
                .claims(claims)
                .subject(email)
                .issuedAt(Date.from(Instant.now()))
                .expiration(getTokenExpirationDate(Instant.now()))
                .issuer(userId)
                .signWith(getKey())
                .compact();
    }

    public String createPaymentToken(String paymentUUID) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.builder()
                .claims(new HashMap<>())
                .subject(paymentUUID)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plus(5, ChronoUnit.MINUTES)))
                //.issuer(userId)
                .signWith(getKey())
                .compact();
    }

    private Key getKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec pbeKeySpec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), ITERATION_COUNT, KEY_SIZE);
        SecretKey pbeKey = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(pbeKeySpec);
        return new SecretKeySpec(pbeKey.getEncoded(), "HmacSHA256");
    }

    private Key getKeyFromInput(String secret) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec pbeKeySpec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), ITERATION_COUNT, KEY_SIZE);
        SecretKey pbeKey = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(pbeKeySpec);
        return new SecretKeySpec(pbeKey.getEncoded(), "HmacSHA256");
    }

    private Date getTokenExpirationDate(Instant now) {
        return Date.from(now.plus(60, ChronoUnit.MINUTES));
    }

    public void verifyToken(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token);
    }

    public void verifyOneTimeToken(String token, String inputSecret) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Jwts.parser()
                .verifyWith((SecretKey) getKeyFromInput(inputSecret))
                .build()
                .parseSignedClaims(token);
    }


    public String getUserNameFromToken(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.parser().verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public String createOneTimeToken(String uuid, String secret) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.builder()
                .claims(new HashMap<>())
                .subject(uuid)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plus(5, ChronoUnit.MINUTES)))
                //.issuer(userId)
                .signWith(getKeyFromInput(secret))
                .compact();
    }
}
