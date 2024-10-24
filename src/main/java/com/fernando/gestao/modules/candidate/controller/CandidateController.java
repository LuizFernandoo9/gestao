package com.fernando.gestao.modules.candidate.controller;


import com.fernando.gestao.dto.ProfileCandidateDTOResponse;
import com.fernando.gestao.modules.candidate.model.CandidateModel;
import com.fernando.gestao.modules.candidate.service.CreateCandidateService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateService createCandidateService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateModel candidateModel){
        try {
            var candidate = this.createCandidateService.execute(candidateModel);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(candidate);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> get(@Valid @RequestBody CandidateModel candidateModel, HttpServletRequest request){
        var candidateId = request.getAttribute("candidate_id");

        try{
            var candidate = this.createCandidateService.profile(UUID.fromString(candidateId.toString()));
            return ResponseEntity.status(HttpStatus.OK).body(candidate);
        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
