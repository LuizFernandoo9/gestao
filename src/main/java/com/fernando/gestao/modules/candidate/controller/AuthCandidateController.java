package com.fernando.gestao.modules.candidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fernando.gestao.dto.AuthCandidateDTORequest;
import com.fernando.gestao.modules.candidate.service.AuthCandidateService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateService authCandidateService;

    @PostMapping("/candidate")
    public ResponseEntity<Object> create(@RequestBody AuthCandidateDTORequest authCandidateDTORequest, HttpServletRequest request){

        try{
            var token = authCandidateService.execute(authCandidateDTORequest);
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }

}
