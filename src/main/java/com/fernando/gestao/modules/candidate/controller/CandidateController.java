package com.fernando.gestao.modules.candidate.controller;

import com.fernando.gestao.exceptions.UserFoundException;
import com.fernando.gestao.modules.candidate.model.CandidateModel;
import com.fernando.gestao.modules.candidate.repository.CandidateRepository;
import com.fernando.gestao.modules.candidate.service.CreateCandidateService;

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
    private CreateCandidateService createCandidateService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateModel candidateModel){
        try {
            var candidate = this.createCandidateService.execute(candidateModel);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(candidateModel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
