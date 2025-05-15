package com.example.BookStore.Service;

import com.example.BookStore.Base_exception.AppException;
import com.example.BookStore.Base_exception.ErrorCode;
import com.example.BookStore.Model.User;
import com.example.BookStore.Repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;
    @NonFinal
    @Value("${signer.key}")
    protected String Signer_Key;

    public Boolean introspect(String token)
            throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(Signer_Key.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(verifier);
        return verified && expiration.after(new Date());
    }
    public String Login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(()->new AppException(ErrorCode.User_Not_Found));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AppException(ErrorCode.Login_Failed);
        }
        String token = generateToken(email,user.getRole());
        return token;
    }
    public String generateToken(String email,String role) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(email)
                .issuer("I'm a good person")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("role", role)
                .claim("authorities", List.of("ROLE_" + role))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(Signer_Key.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }
    public String getEmailFromToken(String token) {
        try {
            // Parse the token
            SignedJWT signedJWT = SignedJWT.parse(token);

            // Verify the token using the signer key
            JWSVerifier verifier = new MACVerifier(Signer_Key.getBytes());
            if (!signedJWT.verify(verifier)) {
                throw new AppException(ErrorCode.Verify_Failed);
            }

            // Extract the claims and get the subject (email)
            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
            return claims.getSubject();
        } catch (ParseException | JOSEException e) {
            throw new AppException(ErrorCode.Verify_Failed);
        }
    }

}