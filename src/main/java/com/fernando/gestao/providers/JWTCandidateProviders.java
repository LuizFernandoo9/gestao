package com.fernando.gestao.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTCandidateProviders {

    @Value("${security.token.secret.cadidate}")
    public String secretyKey;
    Algorithm algorithm = Algorithm.HMAC256(secretyKey);

    public DecodedJWT validateToken(String token){
        token = token.replace("Bearer ", "");
        try {
            var subject = JWT.require(algorithm).build().verify(token);
            return subject;
        } catch (JWTVerificationException e) {
            return null;
        }
    }


}
