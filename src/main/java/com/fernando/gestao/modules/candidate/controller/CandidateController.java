package com.fernando.gestao.modules.candidate.controller;

import com.fernando.gestao.exceptions.UserFoundException;
import com.fernando.gestao.modules.candidate.model.CandidateModel;
import com.fernando.gestao.modules.candidate.repository.CandidateRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping("/")
    public ResponseEntity create(@Valid @RequestBody CandidateModel candidateModel){
        this.candidateRepository.findByUsernameOrEmail(candidateModel.getUsername(), candidateModel.getEmail())
                .ifPresent((user) -> {throw new UserFoundException();
                });

        var candidate = this.candidateRepository.save(candidateModel);
        return ResponseEntity.status(HttpStatus.OK).body(candidate);

    }

}
