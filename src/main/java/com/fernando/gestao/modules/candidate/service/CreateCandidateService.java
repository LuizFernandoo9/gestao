package com.fernando.gestao.modules.candidate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fernando.gestao.exceptions.UserFoundException;
import com.fernando.gestao.modules.candidate.model.CandidateModel;
import com.fernando.gestao.modules.candidate.repository.CandidateRepository;

@Service
public class CreateCandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateModel execute(CandidateModel candidateModel){
        this.candidateRepository.findByUsernameOrEmail(candidateModel.getUsername(), candidateModel.getEmail())
                .ifPresent((user) -> {throw new UserFoundException();
                });

        return this.candidateRepository.save(candidateModel);

    }
}
