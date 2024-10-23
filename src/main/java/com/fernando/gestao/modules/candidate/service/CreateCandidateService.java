package com.fernando.gestao.modules.candidate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fernando.gestao.exceptions.UserFoundException;
import com.fernando.gestao.modules.candidate.model.CandidateModel;
import com.fernando.gestao.modules.candidate.repository.CandidateRepository;

@Service
public class CreateCandidateService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateModel execute(CandidateModel candidateModel){
        this.candidateRepository.findByUsernameOrEmail(candidateModel.getUsername(), candidateModel.getEmail())
                .ifPresent((user) -> {throw new UserFoundException();
                });
        var password = passwordEncoder.encode(candidateModel.getPassword());
        candidateModel.setPassword(password);

        return this.candidateRepository.save(candidateModel);

    }
}
