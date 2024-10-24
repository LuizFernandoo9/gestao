package com.fernando.gestao.modules.candidate.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fernando.gestao.dto.AuthCandidateDTORequest;
import com.fernando.gestao.dto.AuthCandidateDTOResponse;
import com.fernando.gestao.modules.candidate.repository.CandidateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCandidateService {

    @Value("${security.token.secret.cadidate}")
    private String secretyKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateDTOResponse execute(AuthCandidateDTORequest authCandidateDTO){

        var candidate = candidateRepository.findByUsername(authCandidateDTO.username())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Username/ Password incorrect");
                });
        var passwordMatches = passwordEncoder.matches(authCandidateDTO.password(), candidate.getPassword());

        if(!passwordMatches){
            throw new UsernameNotFoundException("Username/ Password incorrect");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretyKey);
        var token = JWT.create()
                .withIssuer("ITEP")
                .withSubject(candidate.getId().toString())
                .withExpiresAt(Instant.now().plus(Duration.ofMinutes(10)))
                .withClaim("roles", Arrays.asList("candidate"))
                .sign(algorithm);

        var authCandidateResponde = AuthCandidateDTOResponse.builder().access_token(token).build();
        return authCandidateResponde;

    }
}
